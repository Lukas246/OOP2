package cz.osu;

import cz.osu.exception.EmptyValueException;
import cz.osu.exception.InvalidEmailException;
import cz.osu.exception.NegativeValueException;
import cz.osu.exception.ShortProductNameException;
import cz.osu.model.Customer;
import cz.osu.model.Order;
import cz.osu.model.ProductItem;
import cz.osu.service.OrderSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static void main() {
        OrderSystem system = new OrderSystem();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Vítejte v systému pro zadávání objednávek!");
        while (true) {
            System.out.println("\n[1] Přidat novou objednávku");
            System.out.println("[2] Zobrazit statistiky");
            System.out.println("[3] Exportovat do CSV");
            System.out.println("[4] Ukončit");
            System.out.print("Vyberte akci: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    try {
                        System.out.println("\n-- Údaje o odběrateli --");
                        System.out.print("Jméno odběratele: ");
                        String jmeno = scanner.nextLine();
                        System.out.print("Email odběratele: ");
                        String email = scanner.nextLine();

                        Customer customer = new Customer(jmeno, email);

                        System.out.println("\n-- Položky objednávky --");
                        List<ProductItem> items = new ArrayList<>();

                        while (true) {
                            System.out.print("Název produktu (nebo 'konec' pro ukončení): ");
                            String nazev = scanner.nextLine();
                            if (nazev.equalsIgnoreCase("konec")) {
                                if (items.isEmpty()) {
                                    System.out.println("Musíte přidat alespoň jednu položku!");
                                    continue;
                                }
                                break;
                            }

                            System.out.print("Počet kusů: ");
                            String mnozstvi = scanner.nextLine();
                            int mnozstviInt = Integer.parseInt(mnozstvi);
                            System.out.print("Cena za kus: ");
                            String cena = scanner.nextLine();
                            double cenaDouble = Double.parseDouble(cena);

                            ProductItem item = new ProductItem(nazev, cenaDouble, mnozstviInt);
                            items.add(item);
                            System.out.println("Položka '" + item.getName() + "' přidána.");
                        }

                        Order order = new Order(customer, items);
                        system.addOrder(order);
                        System.out.println(">> Objednávka byla úspěšně vytvořena a uložena.");

                    } catch (EmptyValueException | NegativeValueException | ShortProductNameException |
                             InvalidEmailException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Neplatný formát čísla! Zadejte prosím platné číslo pro množství a cenu.");
                    }
                    break;
                case "2": {
                    System.out.println("\n--- Statistiky systému ---");
                    System.out.printf(Locale.US, "Celková hodnota objednávek: %.2f%n", system.getTotalRevenue());
                    System.out.printf(Locale.US, "Průměrná hodnota objednávky: %.2f%n", system.getAverageOrderValue());
                    String nej = system.getMostSoldProduct();
                    System.out.println("Nejvíce prodávaný produkt: " + (nej != null ? nej : "Zatím žádná data"));
                    break;
                    }

                case "3": {
                    System.out.print("Zadejte název souboru pro export (např. export.csv): ");
                    String soubor = scanner.nextLine().trim();
                    if (soubor.isEmpty()) {
                        soubor = "export_objednavek.csv";
                    }
                    system.exportToCsv(soubor);
                    break;

                }
                case "4": {
                    System.out.println("Ukončuji program.");
                    System.exit(0);
                    break;

                }
                default: {
                    System.out.println("Neplatná volba, zkuste to znovu.");
                }

            }
        }
    }
}
