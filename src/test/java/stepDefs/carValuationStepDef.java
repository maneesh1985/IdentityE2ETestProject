package stepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.carValuationPage;
import utils.BaseClass;

public class carValuationStepDef extends BaseClass {
    carValuationPage carValuationPage = new carValuationPage();
    @Given("User open browser and lunch URL")
    public void user_open_browser_and_lunch_url() {
        setUp();
    }

   @And("I fetch and validate car registration number are as below:")
    public void iValidateThoseNumberAreAsBelow(DataTable dt) {
        carValuationPage.validateCarRegNumber(dt);
    }

   @And("I check value of car reg number {string} roughly around {string} with mileage as {string}")
    public void iCheckValueOfCarRegNumberCarRegRoughlyAroundMileageWithMileageAsValue(String carreg, String mileage, String value) throws InterruptedException {
        carValuationPage.checkValueOfTheCar(carreg,mileage,value);
    }
}
