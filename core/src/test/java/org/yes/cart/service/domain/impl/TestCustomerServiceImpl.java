/*
 * Copyright 2009 Denys Pavlov, Igor Azarnyi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.yes.cart.service.domain.impl;

import org.junit.Before;
import org.junit.Test;
import org.yes.cart.BaseCoreDBTestCase;
import org.yes.cart.constants.ServiceSpringKeys;
import org.yes.cart.domain.entity.Customer;
import org.yes.cart.service.domain.CustomerService;
import org.yes.cart.service.domain.ShopService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class TestCustomerServiceImpl extends BaseCoreDBTestCase {

    private CustomerService customerService;
    private ShopService shopService;

    @Before
    public void setUp() {
        customerService = (CustomerService) ctx().getBean(ServiceSpringKeys.CUSTOMER_SERVICE);
        shopService = (ShopService) ctx().getBean(ServiceSpringKeys.SHOP_SERVICE);
        super.setUp();
    }

    @Test
    public void testCreate() {
        Customer customer = getCustomer(getTestName());
        customer = customerService.create(customer, shopService.getById(10L));
        assertTrue(customer.getCustomerId() > 0);
        assertFalse(customer.getShops().isEmpty());
    }

    // TODO: YC-64 fix to not depend on order of running
    @Test
    public void testUpdate() {
        Customer customer = getCustomer(getTestName());
        customer = customerService.create(customer, shopService.getById(10L));
        assertTrue(customer.getCustomerId() > 0);
        customer.setFirstname("Gordon");
        customer.setLastname("Freeman");
        customer.setPassword("rawpassword");
        customer = customerService.update(customer);
        assertEquals("Gordon", customer.getFirstname());
        assertEquals("Freeman", customer.getLastname());
    }

    // TODO fix to not depend on order of running
    @Test
    public void testDelete() {
        Customer customer = getCustomer(getTestName());
        customer = customerService.create(customer, shopService.getById(10L));
        assertTrue(customer.getCustomerId() > 0);
        long pk = customer.getCustomerId();
        customerService.delete(customer);
        customer = customerService.findById(pk);
        assertNull(customer);
    }

    //TODO: YC-64 refactor to param test
    @Test
    public void testFindCustomer() {
        Customer customer = getCustomer(getTestName());
        customer.setEmail("user1@somedomain.com");
        customer.setFirstname("SomeFirsname");
        customer.setLastname("user1LastName");
        customer.setPassword("rawpassword");
        customerService.create(customer, shopService.getById(10L));
        customer = getCustomer(getTestName() + "2");
        customer.setFirstname("SomeFirsname");
        customer.setLastname("Akintola");
        customer.setPassword("rawpassword");
        customer.setEmail("user2@somedomain.com");
        customer.setTag("tag1 tag2 tag3");
        customerService.create(customer, shopService.getById(10L));
        List<Customer> list = customerService.findCustomer(getTestName(), null, null, null, null);
        assertNotNull(list);
        list = customerService.findCustomer("user2", null, null, null, null);
        assertEquals(1, list.size());
        list = customerService.findCustomer("somedomain", null, null, null, null);
        assertEquals(2, list.size());
        list = customerService.findCustomer(null, "SomeFirsname", null, null, null);
        assertEquals(2, list.size());
        list = customerService.findCustomer(null, null, "user1LastName", null, null);
        assertEquals(1, list.size());
        list = customerService.findCustomer(null, null, "kintola", null, null);
        assertEquals(1, list.size());
        list = customerService.findCustomer(null, "SomeFirsname", null, null, null);
        assertEquals(2, list.size());
        list = customerService.findCustomer(null, "SomeFirsname", "Akintola", null, null);
        assertEquals(1, list.size());
        list = customerService.findCustomer(null, null, null, null, "tag1");
        assertEquals(1, list.size());
        list = customerService.findCustomer(null, null, null, null, "tag2");
        assertEquals(1, list.size());
        list = customerService.findCustomer(null, null, null, null, "tag3");
        assertEquals(1, list.size());
    }

    private Customer getCustomer(String prefix) {
        Customer customer = customerService.getGenericDao().getEntityFactory().getByIface(Customer.class);
        customer.setEmail(prefix + "customer@shopdomain.com");
        customer.setFirstname(prefix + "Firsname");
        customer.setLastname(prefix + "Lastname");
        customer.setPassword("rawpassword");
        return customer;
    }
}
