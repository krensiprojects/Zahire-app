package al.polis.zahire;

import al.polis.zahire.dto.InsertProductDto;
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
}
