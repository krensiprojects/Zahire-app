package al.polis.zahire;

import al.polis.zahire.dto.InsertProductDto;
import al.polis.zahire.dto.ProductSearchReqDto;
import al.polis.zahire.repository.CatalogueProductRepository;
import al.polis.zahire.service.CatalogueService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ZahireApplicationTests {

	@Autowired
	CatalogueService catalogueService;

	@Autowired
	private CatalogueProductRepository catalogueProductRepository;

	@Test
	@Order(1)
	public void myTest(){
		System.out.println("Starting testing of sum method ...");
		double res = catalogueService.sumTwoNumbers(3,5);
		Assertions.assertEquals(8, res);
		System.out.println("Sum method test is over");
	}

	@Test
	@Order(2)
	public void testInsert(){
		// first empty the table
		catalogueProductRepository.deleteAllInBatch();

		// insert a new product
		InsertProductDto pd = new InsertProductDto();
		pd.setCode("Test01");
		pd.setPrice(1234);
		pd.setDescription("My test description 01");
		pd.setPackageSize(1);
		pd.setPackageWeight(2);
		pd.setMinimumQty(5);
		catalogueService.insertNewProduct(pd);
		// read all products (only one should be there)
		var db = catalogueProductRepository.findAll();
		// check the first and its code
		var first = db.get(0);
		Assertions.assertEquals(pd.getCode(), first.getCode());
		// insert a new product
		pd = new InsertProductDto();
		pd.setCode("Test02");
		pd.setPrice(1234);
		pd.setDescription("My test description 01");
		pd.setPackageSize(1);
		pd.setPackageWeight(2);
		pd.setMinimumQty(5);
		catalogueService.insertNewProduct(pd);
		// read all products (only one should be there)
		db = catalogueProductRepository.findAll();
		// check the first and its code
		first = db.get(1);
		Assertions.assertEquals("Test02", first.getCode());
	}

	@Test
	@Order(3)
	public void testProductCatalogueRepository(){
		// test that there are two elements
		var whole = catalogueProductRepository.findTheWholeCatalogue();
		Assertions.assertEquals(2, whole.size());
		// empty table
		catalogueProductRepository.deleteAll();

		// test that there are 0 elements
		var nothing = catalogueProductRepository.findTheWholeCatalogue();
		Assertions.assertEquals(0, nothing.size());

	}
	@Test
	@Order(4)
	public void testSearchInCatalogue() {
		// Step 1: Clear the database
		catalogueProductRepository.deleteAll();

		// Step 2: Insert test data into the catalogue
		InsertProductDto product1 = new InsertProductDto();
		product1.setCode("Prod01");
		product1.setPrice(500);
		product1.setDescription("Apple Juice");
		product1.setPackageSize(1);
		product1.setPackageWeight(1.5);
		product1.setMinimumQty(10);
		catalogueService.insertNewProduct(product1);

		InsertProductDto product2 = new InsertProductDto();
		product2.setCode("Prod02");
		product2.setPrice(700);
		product2.setDescription("Orange Juice");
		product2.setPackageSize(2);
		product2.setPackageWeight(2.0);
		product2.setMinimumQty(5);
		catalogueService.insertNewProduct(product2);

		InsertProductDto product3 = new InsertProductDto();
		product3.setCode("Prod03");
		product3.setPrice(300);
		product3.setDescription("Grape Juice");
		product3.setPackageSize(1);
		product3.setPackageWeight(1.0);
		product3.setMinimumQty(8);
		catalogueService.insertNewProduct(product3);

		// Step 3: Perform the search (e.g., search for "juice")
		ProductSearchReqDto searchRequest = new ProductSearchReqDto();
		searchRequest.setSearchCriterion("juice");
		var searchResults = catalogueService.searchInCatalogue(searchRequest);

		// Step 4: Validate the results
		Assertions.assertEquals(3, searchResults.size(), "Search should return 3 products.");
		Assertions.assertTrue(
				searchResults.stream().anyMatch(p -> p.getDescription().equalsIgnoreCase("Apple Juice")),
				"Search results should contain 'Apple Juice'."
		);
		Assertions.assertTrue(
				searchResults.stream().anyMatch(p -> p.getDescription().equalsIgnoreCase("Orange Juice")),
				"Search results should contain 'Orange Juice'."
		);
		Assertions.assertTrue(
				searchResults.stream().anyMatch(p -> p.getDescription().equalsIgnoreCase("Grape Juice")),
				"Search results should contain 'Grape Juice'."
		);

		// Step 5: Clean up (database already cleared in test setup)
	}

}
