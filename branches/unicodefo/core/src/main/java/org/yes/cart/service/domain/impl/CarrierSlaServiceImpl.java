package org.yes.cart.service.domain.impl;

import org.hibernate.criterion.Restrictions;
import org.yes.cart.dao.GenericDAO;
import org.yes.cart.domain.entity.Address;
import org.yes.cart.domain.entity.CarrierSla;
import org.yes.cart.domain.entity.CustomerOrderDeliveryDet;
import org.yes.cart.service.domain.CarrierSlaService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class CarrierSlaServiceImpl extends BaseGenericServiceImpl<CarrierSla> implements CarrierSlaService {

    /**
     * Construct Service.
     * @param genericDao dao to use.
     */
    public CarrierSlaServiceImpl(final GenericDAO<CarrierSla, Long> genericDao) {
        super(genericDao);
    }


    /** {@inheritDoc} */
    public CarrierSla finaByName(final String slaName) {
        return getGenericDao().findSingleByCriteria(Restrictions.eq("name", slaName));
    }

    /** {@inheritDoc} */
    public List<CarrierSla> findByCarrier(final long carrierId) {
        return getGenericDao().findByNamedQuery("CARRIER.SLA.BY.CARRIER", carrierId);
    }

    /** {@inheritDoc} */
    public BigDecimal getDeliveryPrice(final CarrierSla carrierSla,
                                       final Collection<CustomerOrderDeliveryDet> items,
                                       final Address defaultAddress) {
        // TODO v2 at this moment fixed or zero delivery prices are supported, so just return the price from sla
        if (carrierSla == null) {
            return BigDecimal.ZERO;
        }
        return carrierSla.getPrice();
    }

    /**
     * {@inheritDoc}
     */
    public List<CarrierSla> findByCurrency(final String currency) {
        return findByCriteria(Restrictions.eq("currency", currency));
    }
    

}