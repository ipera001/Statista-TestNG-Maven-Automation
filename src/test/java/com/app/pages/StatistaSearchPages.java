package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class StatistaSearchPages {

	private WebDriver driver;

	public StatistaSearchPages() {
		this.driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "q")
	public WebElement searchBoxText;

	@FindBy(xpath = "//h2[@class='h5 text-color--darkerblue margin-bottom-0 text-ellipsis']")
	public List<WebElement> ResultsListAfterSearch;

	@FindBy(xpath = "//h4[@class='hl-module hideMobile']//span[@class='text-normal']")
	public WebElement SearchResultNumber;

	@FindBy(id = "refreshBtn")
	public WebElement refreshSearch;

	@FindBy(xpath = "//label[@class='entitiy__label']")
	public List<WebElement> filtersNameLists;

	public static boolean DynamicDropMenuResults(List<WebElement> element, String search) {

		for (WebElement elmt : element) {

			if (elmt.getText().toLowerCase().contains(search.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	// Method return the element radio button desired under Search Accuracy by
	// entering its exact String value
	public WebElement RadioButton(String str) {
		return driver
				.findElement(By.xpath("//div[@class='form_row__radio width33']//label[.='" + str + "']/..//input"));

	}

	public boolean isClickable() {
		if (driver.getTitle().contains("Search | Statista")) {
			return true;
		}

		return false;
	}

	public WebElement FilerElement(List<WebElement> list, String filter) {
		WebElement Filter = null;
		int index = 0;
		for (WebElement webElement : list) {

			if (webElement.getText().toLowerCase().contains(filter.toLowerCase())) {

				Filter = webElement;

			}
			index++;

		}
		return Filter;

	}

	@FindBy(xpath = "//span[@class='font-italic']/span")
	public List<WebElement> resultlistfilter;

	public int resultNumberFilter(String str, List<WebElement> list) {
		int index = 0;

		for (WebElement webElement : list) {

			if (webElement.getText().toLowerCase().contains(str.toLowerCase())) {

				break;
			}
			index++;

		}

		return Integer.parseInt(resultlistfilter.get(index).getText());

	}

	@FindBy(xpath = "//td/a")
	public List<WebElement> listRsultAfterFilter;

	@FindBy(xpath = "//input[@class='entitiy__checkbox']")
	public List<WebElement> topicCheckBoxes;

	public boolean verifingCheckBoxIsUnchecked(List<WebElement> elements, String name) {

		for (int i = 0; i < elements.size(); i++) {
			if (!filtersNameLists.get(i).getText().contains(name)) {

				if (elements.get(i).getAttribute("checked") == null) {

					return true;
				} else {

					continue;
				}
			}
		}
		return false;
	}

	public WebElement CheckBoxElement(String str, List<WebElement> list) {
		int index = 0;

		for (WebElement webElement : list) {

			if (webElement.getText().toLowerCase().contains(str.toLowerCase())) {

				break;
			}
			index++;

		}

		return topicCheckBoxes.get(index);

	}

}