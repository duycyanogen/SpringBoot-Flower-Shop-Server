package DemoApp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public class InventoryProductImage {
	@JsonProperty(value="ProductID")
	private int ProductID;
	@JsonProperty(value="Image")
	private String Image;
	@JsonProperty(value="ProductName")
	private String ProductName;
	@JsonProperty(value="productCode")
	private String ProductCode;
	

}

