import org.example.Google;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {
    @BeforeMethod
    public void setup() {
        Google.setup();
        Google.scrollDown();
        Google.closeCookies();
    }
    @Test (priority = 1)
    public void isEnabledDisplayed() {
       boolean resultActual = Google.isEnabledDisplayed(By.className("gLFyf"));
        Assert.assertTrue(resultActual);
    }
    @Test (priority = 2)
    public void isEnabledDisplayedNegative() {
        boolean resultActual = Google.isEnabledDisplayed(By.className("gLFyf"));
        Assert.assertNotEquals(false, resultActual);
    }
    @Test (priority = 3)
    public void serchByKeyword() {
        Google.searchByKeyword("meditacija");
    }
    @Test (priority = 5)
    public void compareResultPositive() {
        Google.searchByKeyword("meditacija");
        int resultInt = Google.getResult();
        String resultActual = Google.compareResult(resultInt);
        Assert.assertEquals("Tai yra dažnai paieškoje vedamas žodis", resultActual);
    }
    @Test (priority = 4)
    public void compareResultNegative() {
        Google.searchByKeyword("meditacija");
        int resultInt = Google.getResult();
        String resultActual = Google.compareResult(resultInt);
        Assert.assertNotEquals("Tai yra rečiau ieškomas žodis", resultActual);
    }


    @AfterMethod
    public void close() {Google.close();}

}
