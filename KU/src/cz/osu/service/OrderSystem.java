package cz.osu.service;

import cz.osu.model.Order;
import cz.osu.model.ProductItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderSystem {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }
    public double getTotalRevenue() {
        return orders.stream().mapToDouble(Order::getOrderTotal).sum();
    }
    public double getAverageOrderValue() {
        if (orders.isEmpty()) {
            return 0;
        }
        return getTotalRevenue() / orders.size();
    }
    public String getMostSoldProduct() {
        if (orders.isEmpty()) {
            return "Žádné objednávky";
    }
        Map<String, Integer> productSales = new HashMap<>();
        for (Order order : orders) {
            for (ProductItem item : order.getItems()) {
                productSales.put(item.getName(), productSales.getOrDefault(item.getName(), 0) + item.getQuantity());

            }
        }
        return productSales.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Žádné produkty");
    }
    public void exportToCsv(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Datum;Jmeno Zakaznika;Celkova Cena");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Order order : orders) {
                String dateStr = order.getOrderDate().format(formatter);
                String customerName = order.getCustomer().getName();
                double total = order.getOrderTotal();
                writer.printf(Locale.US, "%s;%s;%.2f%n", dateStr, customerName, total);
            }
            System.out.println(">> Úspěšně exportováno do souboru: " + filename);
        } catch (IOException e) {
            System.out.println(">> Chyba při zápisu do souboru: " + e.getMessage());
        }
    }
}
