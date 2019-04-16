package ru.job4j.solid.lsp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.04.2019
 */
public class ControlQualityTest {

    private Warehouse warehouse;
    private Shop shop;
    private Thrash thrash;

    @Before
    public void clearProducts() {
        warehouse = new Warehouse();
        shop = new Shop();
        thrash = new Thrash();
    }

    @Test
    public void whenThresholdMore75ThenAddToWarehouse() {
        Food bread1 = new Bread("bread 1", "10.05.2022",
                "01.01.2019", 50.00, 10);
        Food bread2 = new Bread("bread 2", "30.05.2022",
                "01.01.2019", 60.00, 15);
        Food milk1 = new Milk("milk 1", "30.05.2019",
                "15.04.2019", 45.00, 20);
        Food milk2 = new Milk("milk 2", "30.05.2019",
                "18.04.2019", 80.00, 30);
        ControlQuality cq = new ControlQuality(warehouse, shop, thrash);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        System.out.println(warehouse.getProducts().size());
        System.out.println(shop.getProducts().size());
        System.out.println(thrash.getProducts().size());
        assertThat(warehouse.getProducts().size(), is(4));
    }

    @Test
    public void whenThresholdLess75ThenAddToStore() {
        Food bread1 = new Bread("bread 1", "10.05.2019",
                "01.01.2019", 50.00, 10);
        Food bread2 = new Bread("bread 2", "30.05.2019",
                "01.01.2019", 60.00, 15);
        Food milk1 = new Milk("milk 1", "10.05.2019",
                "15.03.2019", 45.00, 20);
        Food milk2 = new Milk("milk 2", "01.05.2019",
                "18.03.2019", 80.00, 30);
        ControlQuality cq = new ControlQuality(warehouse, shop, thrash);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        System.out.println(warehouse.getProducts().size());
        System.out.println(shop.getProducts().size());
        System.out.println(thrash.getProducts().size());
        assertThat(shop.getProducts().size(), is(4));
    }

    @Test
    public void whenThreshold0ThenAddToThrash() {
        Food bread1 = new Bread("bread 1", "10.04.2019",
                "01.01.2019", 50.00, 10);
        Food bread2 = new Bread("bread 2", "15.04.2019",
                "01.01.2019", 60.00, 15);
        Food milk1 = new Milk("milk 1", "10.04.2019",
                "15.03.2019", 45.00, 20);
        Food milk2 = new Milk("milk 2", "11.04.2019",
                "18.03.2019", 80.00, 30);
        ControlQuality cq = new ControlQuality(warehouse, shop, thrash);
        cq.sendToStore(bread1);
        cq.sendToStore(bread2);
        cq.sendToStore(milk1);
        cq.sendToStore(milk2);
        System.out.println(warehouse.getProducts().size());
        System.out.println(shop.getProducts().size());
        System.out.println(thrash.getProducts().size());
        assertThat(thrash.getProducts().size(), is(4));
    }
}
