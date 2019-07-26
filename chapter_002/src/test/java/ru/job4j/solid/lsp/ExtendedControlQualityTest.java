package ru.job4j.solid.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.solid.lsp.eventloop.*;
import ru.job4j.solid.lsp.eventloop.extended.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 24.07.2019
 */
public class ExtendedControlQualityTest {

    private WarehouseHandler warehouse;
    private ThrashHandler thrash;
    private ShopHandler shop;
    private Recycler recycler = new Recycler();
    private Freezer freezer = new Freezer();
    private LocalDate date = LocalDate.now();
    private ExtendedControlQuality cq = new ExtendedControlQuality();
    private List<FoodHandler> stores = new ArrayList<>();

    @Before
    public void init() {
        warehouse = new WarehouseHandler(new Warehouse(), cq.getMaxSpace());
        thrash = new ThrashHandler(new Thrash());
        shop = new ShopHandler(new Shop());
        stores.add(warehouse);
        stores.add(shop);
        stores.add(thrash);
        stores.add(freezer);
        stores.add(recycler);
    }

    @Test
    public void whenThresholdLess75ThenAddToStore() {
        cq.initHandler(stores);
        Food bread1 = new ExtendedFood("bread 1", date.plusDays(10).toString(),
                date.minusDays(50).toString(), 50.00, 10);
        Food bread2 = new ExtendedFood("bread 2", date.plusDays(40).toString(),
                date.minusDays(50).toString(), 60.00, 15);
        Food milk1 = new ExtendedFood("milk 1", date.plusDays(5).toString(),
                date.minusDays(5).toString(), 45.00, 20);
        Food milk2 = new ExtendedFood("milk 2", date.plusDays(1).toString(),
                date.minusDays(5).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof ShopHandler) {
                shop = (ShopHandler) handler;
                break;
            }
        }
        assertThat(shop.getProducts().size(), is(4));
    }

    @Test
    public void whenThresholdMore75ThenAddToWarehouse() {
        cq.initHandler(stores);
        Food bread1 = new ExtendedFood("bread 1", date.plusDays(100).toString(),
                date.minusDays(10).toString(), 50.00, 10);
        Food bread2 = new ExtendedFood("bread 2", date.plusDays(80).toString(),
                date.minusDays(10).toString(), 60.00, 15);
        Food milk1 = new ExtendedFood("milk 1", date.plusDays(10).toString(),
                date.minusDays(1).toString(), 45.00, 20);
        Food milk2 = new ExtendedFood("milk 2", date.plusDays(8).toString(),
                date.minusDays(1).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof WarehouseHandler) {
                warehouse = (WarehouseHandler) handler;
                break;
            }
        }
        assertThat(warehouse.getProducts().size(), is(4));
    }

    @Test
    public void whenThreshold0ThenAddToThrash() {
        cq.initHandler(stores);
        Food bread1 = new ExtendedFood("bread 1", date.minusDays(1).toString(),
                date.minusDays(10).toString(), 50.00, 10);
        Food bread2 = new ExtendedFood("bread 2", date.minusDays(2).toString(),
                date.minusDays(10).toString(), 60.00, 15);
        Food milk1 = new ExtendedFood("milk 1", date.toString(),
                date.minusDays(2).toString(), 45.00, 20);
        Food milk2 = new ExtendedFood("milk 2", date.minusDays(1).toString(),
                date.minusDays(2).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof ThrashHandler) {
                thrash = (ThrashHandler) handler;
                break;
            }
        }
        assertThat(thrash.getProducts().size(), is(4));
    }

    @Test
    public void whenCanReproductThenAddToRecucler() {
        cq.initHandler(stores);
        Food bread1 = new ExtendedFood("bread 1", date.minusDays(1).toString(),
                date.minusDays(10).toString(), 50.00, 10, false, true);
        Food bread2 = new ExtendedFood("bread 2", date.minusDays(2).toString(),
                date.minusDays(10).toString(), 60.00, 15, false, true);
        Food milk1 = new ExtendedFood("milk 1", date.toString(),
                date.minusDays(2).toString(), 45.00, 20);
        Food milk2 = new ExtendedFood("milk 2", date.minusDays(1).toString(),
                date.minusDays(2).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        assertThat(recycler.getProducts().size(), is(2));
    }

    @Test
    public void whenVegetableThenAddFreezer() {
        cq.initHandler(stores);
        Food bread1 = new ExtendedFood("bread 1", date.plusDays(100).toString(),
                date.minusDays(10).toString(), 50.00, 10, true, false);
        Food bread2 = new ExtendedFood("bread 2", date.plusDays(80).toString(),
                date.minusDays(10).toString(), 60.00, 15, true, true);
        Food milk1 = new ExtendedFood("milk 1", date.plusDays(10).toString(),
                date.minusDays(1).toString(), 45.00, 20);
        Food milk2 = new ExtendedFood("milk 2", date.plusDays(8).toString(),
                date.minusDays(1).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        assertThat(freezer.getProducts().size(), is(2));
    }

    @Test
    public void whenWarehouseFullThenAddNewWarehouse() {
        stores.clear();
        cq.setMaxSpace(3);
        warehouse = new WarehouseHandler(new Warehouse(), cq.getMaxSpace());
        thrash = new ThrashHandler(new Thrash());
        shop = new ShopHandler(new Shop());
        stores.add(warehouse);
        stores.add(shop);
        stores.add(thrash);
        cq.initHandler(stores);
        Food bread1 = new ExtendedFood("bread 1", date.plusDays(100).toString(),
                date.minusDays(10).toString(), 50.00, 10);
        Food bread2 = new ExtendedFood("bread 2", date.plusDays(80).toString(),
                date.minusDays(10).toString(), 60.00, 15);
        Food milk1 = new ExtendedFood("milk 1", date.plusDays(10).toString(),
                date.minusDays(1).toString(), 45.00, 20);
        Food milk2 = new ExtendedFood("milk 2", date.plusDays(8).toString(),
                date.minusDays(1).toString(), 80.00, 30);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        for (FoodHandler handler : cq.getStores()) {
            if (handler instanceof WarehouseHandler) {
                warehouse = (WarehouseHandler) handler;
                break;
            }
        }
        assertThat(stores.size(), is(4));
    }
}
