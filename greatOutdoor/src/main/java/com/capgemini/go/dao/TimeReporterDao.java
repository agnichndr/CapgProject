/**
 * Time Reporter Interface
 * 
 * Contributers: Kunal Roychoudhury; Vikas Singh; Sujit Kumar
 */

package com.capgemini.go.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.go.model.Product;
import com.capgemini.go.model.RetailerInventoryItem;

public interface TimeReporterDao {
	/**
	 * @param adminId
	 * @param retailerId
	 * @param requestDate
	 * @return a list of products (collection | ArrayList)
	 * 
	 *         this product list must be read and details fed into the report will
	 *         be called as TimeReporterDao.getMontlyReport (goAdmin.getUserId(),
	 *         retailer.getUserId(), requestDate)
	 */
	List<RetailerInventoryItem> getMonthlyReport(String adminId, String retailerId, Date requestDate);

	/**
	 * @param adminId
	 * @param retailerId
	 * @param requestDateInitial
	 * @param requestDateFinal
	 * @return a list of products (collection | ArrayList)
	 * 
	 *         this product list must be read and details fed into the report will
	 *         be called as TimeReporterDao.getQuarterlyReport (goAdmin.getUserId(),
	 *         retailer.getUserId(), requestDateInitial, requestDateFinal)
	 */
	List<RetailerInventoryItem> getQuarterlyReport(String adminId, String retailerId, Date requestDateInitial,
			Date requestDateFinal);

	/**
	 * @param adminId
	 * @param retailerId
	 * @param requestDate
	 * @return a list of products (collection | ArrayList)
	 * 
	 *         this product list must be read and details fed into the report will
	 *         be called as TimeReporterDao.getYearlyReport (goAdmin.getUserId(),
	 *         retailer.getUserId(), requestDate)
	 */
	List<RetailerInventoryItem> getYearlyReport(String adminId, String retailerId, Date requestDate);

	/**
	 * @param adminId
	 * @param retailerId
	 * @param productId
	 * @param cat
	 * @return a list of products (collection | ArrayList)
	 * 
	 *         this product list must be read and details fed into the report will
	 *         be called as TimeReporterDao.getOutlierProductCategoryDeliveryTime
	 *         (goAdmin.getUserId(), retailer.getUserId(), Product.category.CAMPING)
	 */
	List<RetailerInventoryItem> getOutlierProductCategoryDeliveryTime(String adminId, String retailerId,
			String productId, Product.category cat);

	/**
	 * @param adminId
	 * @param retailerId
	 * @param orderId
	 * @param cat
	 * @return a list of products (collection | ArrayList)
	 * 
	 *         this product list must be read and details fed into the report will
	 *         be called as TimeReporterDao.getOutlierItemsDeliveryItem
	 *         (goAdmin.getUserId(), retailer.getUserId())
	 */
	List<RetailerInventoryItem> getOutlierItemsDeliveryTime(String adminId, String retailerId, String orderId,
			Product.category cat);

	/**
	 * @param adminId
	 * @param retailerId
	 * @param cat
	 * @return a list of products (collection | ArrayList)
	 * 
	 *         this product list must be read and details fed into the report will
	 *         be called as TimeReporterDao.getOutlierItemsInCategoryDeliveryTime
	 *         (goAdmin.getUserId(), retailer.getUserId(), Product.category.CAMPING)
	 */
	List<RetailerInventoryItem> getOutlierItemsInCategoryDeliveryTime(String adminId, String retailerId,
			Product.category cat);
}
