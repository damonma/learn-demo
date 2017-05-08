package com.damon.designPattern.mediator;

/**
 * MediatorTest
 *
 * @author damon
 * @date 2017/5/8
 */
public class MediatorTest {

    public static void main(String[] args) {
        MediatorStructure mediator = new MediatorStructure();

        HouseOwner houseOwner = new HouseOwner("qq",mediator);
        Tenant tenant = new Tenant("jj",mediator);

        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        tenant.contact("I wanna a house");
        houseOwner.contact("I have~");
    }
}
