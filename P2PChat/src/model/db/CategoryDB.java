package model.db;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class CategoryDB {

	private Hashtable<String, List<String>> categories;
	private List<String> listCategories;
	
	
	public CategoryDB() {}
	
	@PostConstruct
	private void init() {
		this.categories = new Hashtable<>();
		this.listCategories = new ArrayList<>();
		
		this.listCategories.add("category 1");
		this.listCategories.add("category 2");
		this.listCategories.add("category 3");
		this.listCategories.add("category 4");
		
		List<String> subCat1 = new ArrayList<String>();
		List<String> subCat2 = new ArrayList<String>();
		List<String> subCat3 = new ArrayList<String>();
		List<String> subCat4 = new ArrayList<String>();
		
		subCat1.add("sub1");
		subCat1.add("sub2");
		
		subCat2.add("sub3");
		subCat2.add("sub4");
		
		subCat3.add("sub5");
		subCat3.add("sub6");
		
		subCat4.add("sub7");
		subCat4.add("sub8");
		
		this.categories.put(this.listCategories.get(0), subCat1);
		this.categories.put(this.listCategories.get(1), subCat2);
		this.categories.put(this.listCategories.get(2), subCat3);
		this.categories.put(this.listCategories.get(3), subCat4);
	}
	
	public Set<String> getCategories() {
		return this.categories.keySet();
	}
	
	public List<String> getSubcategories(String category) {
		return this.categories.get(category);
	}
	
	public String getFirstCategory() {
		return this.getCategories().iterator().next();
	}
}
