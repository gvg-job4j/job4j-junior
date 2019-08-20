package ru.job4j.solid.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.solid.lsp.eventloop.*;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 05.06.2019
 */
public class ControlQualityEventLoopTest {

    private Warehouse warehouse = new Warehouse();
    private Thrash thrash = new Thrash();
    private Shop shop = new Shop();
    private LocalDate date = LocalDate.now();
    private ControlQuality cq = new ControlQuality();

    @Before
    public void init() {
        cq.initHandler();
    }

    @Test
    public void whenThresholdLess75ThenAddToStore() {
        Food bread1 = new Bread("bread 1", date.plusDays(10).toString(),
                date.minusDays(50).toString(), 50.00, 10);
        Food bread2 = new Bread("bread 2", date.plusDays(40).toString(),
                date.minusDays(50).toString(), 60.00, 15);
        Food milk1 = new Milk("milk 1", date.plusDays(5).toString(),
                date.minusDays(5).toString(), 45.00, 20);
        Food milk2 = new Milk("milk 2", date.plusDays(1).toString(),
                date.minusDays(5).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Shop) {
                shop = (Shop) handler;
                break;
            }
        }
        assertThat(shop.getProducts().size(), is(4));
    }

    @Test
    public void whenThresholdMore75ThenAddToWarehouse() {
        Food bread1 = new Bread("bread 1", date.plusDays(100).toString(),
                date.minusDays(10).toString(), 50.00, 10);
        Food bread2 = new Bread("bread 2", date.plusDays(80).toString(),
                date.minusDays(10).toString(), 60.00, 15);
        Food milk1 = new Milk("milk 1", date.plusDays(10).toString(),
                date.minusDays(1).toString(), 45.00, 20);
        Food milk2 = new Milk("milk 2", date.plusDays(8).toString(),
                date.minusDays(1).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Warehouse) {
                warehouse = (Warehouse) handler;
                break;
            }
        }
        assertThat(warehouse.getProducts().size(), is(4));
    }

    @Test
    public void whenThreshold0ThenAddToThrash() {
        Food bread1 = new Bread("bread 1", date.minusDays(1).toString(),
                date.minusDays(10).toString(), 50.00, 10);
        Food bread2 = new Bread("bread 2", date.minusDays(2).toString(),
                date.minusDays(10).toString(), 60.00, 15);
        Food milk1 = new Milk("milk 1", date.toString(),
                date.minusDays(2).toString(), 45.00, 20);
        Food milk2 = new Milk("milk 2", date.minusDays(1).toString(),
                date.minusDays(2).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Thrash) {
                thrash = (Thrash) handler;
                break;
            }
        }
        assertThat(thrash.getProducts().size(), is(4));
    }

    @Test
    public void whenResortThreshold0ThenAddToThrash() {
        Food bread1 = new Bread("bread 1", date.minusDays(1).toString(),
                date.minusDays(10).toString(), 50.00, 10);
        Food bread2 = new Bread("bread 2", date.minusDays(2).toString(),
                date.minusDays(10).toString(), 60.00, 15);
        Food milk1 = new Milk("milk 1", date.toString(),
                date.minusDays(2).toString(), 45.00, 20);
        Food milk2 = new Milk("milk 2", date.minusDays(1).toString(),
                date.minusDays(2).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Thrash) {
                thrash = (Thrash) handler;
                break;
            }
        }
        int thrashCount = thrash.getProducts().size();
        cq.resort();
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Thrash) {
                thrash = (Thrash) handler;
                break;
            }
        }
        assertThat(thrash.getProducts().size(), is(thrashCount));
    }

    @Test
    public void whenResortThresholdLess75ThenAddToStore() {
        Food bread1 = new Bread("bread 1", date.plusDays(10).toString(),
                date.minusDays(50).toString(), 50.00, 10);
        Food bread2 = new Bread("bread 2", date.plusDays(40).toString(),
                date.minusDays(50).toString(), 60.00, 15);
        Food milk1 = new Milk("milk 1", date.plusDays(5).toString(),
                date.minusDays(5).toString(), 45.00, 20);
        Food milk2 = new Milk("milk 2", date.plusDays(1).toString(),
                date.minusDays(5).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Shop) {
                shop = (Shop) handler;
                break;
            }
        }
        int shopCount = shop.getProducts().size();
        cq.resort();
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Shop) {
                shop = (Shop) handler;
                break;
            }
        }
        assertThat(shop.getProducts().size(), is(shopCount));
    }

    @Test
    public void whenResortThresholdMore75ThenAddToWarehouse() {
        Food bread1 = new Bread("bread 1", date.plusDays(100).toString(),
                date.minusDays(10).toString(), 50.00, 10);
        Food bread2 = new Bread("bread 2", date.plusDays(80).toString(),
                date.minusDays(10).toString(), 60.00, 15);
        Food milk1 = new Milk("milk 1", date.plusDays(10).toString(),
                date.minusDays(1).toString(), 45.00, 20);
        Food milk2 = new Milk("milk 2", date.plusDays(8).toString(),
                date.minusDays(1).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Warehouse) {
                warehouse = (Warehouse) handler;
                break;
            }
        }
        int warehouseCount = warehouse.getProducts().size();
        cq.resort();
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof Warehouse) {
                warehouse = (Warehouse) handler;
                break;
            }
        }
        assertThat(warehouse.getProducts().size(), is(warehouseCount));
    }
}
