package businesscomponents.mypolicygalen;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import webelementrepository.MyPolicy.DashboardPage;

import com.Cucumber.supportLibraries.DriverManager;
import com.Cucumber.supportLibraries.StaticValue;
import com.Cucumber.supportLibraries.Webaction;
import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

public class GalenFunctions extends Webaction implements DashboardPage {

	WebDriver driver = DriverManager.getWebDriver();
	StaticValue staticvalue = new StaticValue();

	public void testdashboardPage() {
		try {
			LayoutReport layoutReport = Galen.checkLayout(driver,
					"specs/addvehicle.gspec", Arrays.asList("desktop"));

			List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
			GalenTestInfo test = GalenTestInfo
					.fromString("dashboardpage layout");
			test.getReport().layout(layoutReport, "check dashboard layout");
			tests.add(test);
			HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
			htmlReportBuilder.build(tests, "target");
			/*
			 * 
			 * if (layoutReport.errors() > 0) {
			 * Assert.fail("Layout test failed"); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testMakePolicyChanges() {
		try {
			LayoutReport layoutReport = Galen.checkLayout(driver,
					"specs/MakePolicyChanges.gspec", Arrays.asList("desktop"));

			List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();
			GalenTestInfo test = GalenTestInfo
					.fromString("dashboardpage layout");
			test.getReport().layout(layoutReport, "check dashboard layout");
			tests.add(test);
			HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
			htmlReportBuilder.build(tests, "target");
			/*
			 * 
			 * if (layoutReport.errors() > 0) {
			 * Assert.fail("Layout test failed"); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
