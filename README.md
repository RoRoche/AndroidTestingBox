# AndroidTestingBox

Android project to experiment various testing tools.
It targets **Java** and **Kotlin** languages.
Priority is given to fluency and ease of use.
The idea is to provide a toolbox to write elegant and intelligible tests, with modern techniques like **behavior-driven testing frameworks** or **fluent assertions**.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidTestingBox-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/4658)
[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23242-green.svg)](http://androidweekly.net/issues/issue-242)
[![Dependency Status](https://www.versioneye.com/user/projects/58b85f8401b5b7003a2129e7/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/58b85f8401b5b7003a2129e7)

![logo](https://raw.githubusercontent.com/RoRoche/AndroidTestingBox/master/assets/logo.png)

<!-- run the following command line: markdown-toc -i README.md -->

<!-- toc -->

- [AndroidTestingBox in the news](#androidtestingbox-in-the-news)
- [System under test (SUT)](#system-under-test-sut)
  * [Simple Java class](#simple-java-class)
  * [Android `Activity`](#android-activity)
- [JUnit](#junit)
  * [Fluent assertions: truth](#fluent-assertions-truth)
    + [Alternative: AssertJ](#alternative-assertj)
  * [Frutilla](#frutilla)
  * [Fluent test method names](#fluent-test-method-names)
  * [Specifications framework: Spectrum](#specifications-framework-spectrum)
    + [Alternative: Oleaster](#alternative-oleaster)
  * [Hierarchies in JUnit: junit-hierarchicalcontextrunner](#hierarchies-in-junit-junit-hierarchicalcontextrunner)
    + [Novelty to consider: JUnit 5 Nested Tests](#novelty-to-consider-junit-5-nested-tests)
  * [BDD tools](#bdd-tools)
    + [Cucumber](#cucumber)
    + [JGiven](#jgiven)
  * [Mutation testing: Zester plugin](#mutation-testing-zester-plugin)
  * [Alternative to JUnit: TestNG](#alternative-to-junit-testng)
- [Kotlin](#kotlin)
  * [Fluent assertions: Kluent](#fluent-assertions-kluent)
    + [Alternative: Expekt](#alternative-expekt)
  * [Specifications framework: Spek](#specifications-framework-spek)
- [Android](#android)
  * [Fluent assertions: AssertJ Android](#fluent-assertions-assertj-android)
  * [Robotium](#robotium)
  * [Espresso](#espresso)
  * [Robolectric](#robolectric)
  * [Cucumber support](#cucumber-support)
  * [JGiven support](#jgiven-support)
- [IDE configuration](#ide-configuration)
- [Nota Bene](#nota-bene)
- [Bibliography](#bibliography)
- [Interesting repositories](#interesting-repositories)
- [Interesting articles](#interesting-articles)
- [Resources](#resources)
- [Logo credits](#logo-credits)

<!-- tocstop -->

## AndroidTestingBox in the news

* [Android Weekly #242](http://androidweekly.net/issues/issue-242)

## System under test (SUT)

### Simple Java class

```java
public class Sum {
    public final int a;
    public final int b;
    private final LazyInitializer<Integer> mSum;

    public Sum(int a, int b) {
        this.a = a;
        this.b = b;
        mSum = new LazyInitializer<Integer>() {
            @Override
            protected Integer initialize() throws ConcurrentException {
                return Sum.this.a + Sum.this.b;
            }
        };
    }

    public int getSum() throws ConcurrentException {
        return mSum.get();
    }
}
```

### Android `Activity`

Here stands the layout file:

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    android:id="@+id/activity_main"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/ActivityMain_TextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="@string/app_name"/>

    <Button
        android:id="@+id/ActivityMain_Button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/ActivityMain_TextView"
        android:layout_centerHorizontal="true"
        android:text="@string/click_me"/>
</RelativeLayout>

```

and here stands the corresponding `Activity`:

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.ActivityMain_TextView) as TextView
        val button = findViewById(R.id.ActivityMain_Button)
        button.setOnClickListener({ view: View -> textView.setText(R.string.text_changed_after_button_click) })
    }
}
```

## JUnit

### Fluent assertions: truth

- <https://google.github.io/truth/>

#### Alternative: AssertJ

* <http://joel-costigliola.github.io/assertj/>

### Frutilla

- <https://github.com/ignaciotcrespo/frutilla>

```java
@RunWith(value = org.frutilla.FrutillaTestRunner.class)
public class FrutillaSumTest {

    @Frutilla(
            Given = "two numbers a = 1 and b = 3",
            When = "computing the sum of these 2 numbers",
            Then = "should compute sum = 4"
    )
    @Test
    public void test_addition_isCorrect() throws Exception {
        given("two numbers", () -> {
            final int a = 1;
            final int b = 3;

            when("computing the sum of these 2 numbers", () -> {
                final Sum sum = new Sum(a, b);

                then("should compute sum = 4", () -> assertThat(sum.getSum()).isEqualTo(4));
            });
        });
    }
}
```

### Fluent test method names

- <https://jijeshmohan.wordpress.com/2011/12/17/junit-readable-test-names/>

### Specifications framework: Spectrum

- <https://github.com/greghaskins/spectrum>
- <http://www.greghaskins.com/archive/introducing-spectrum-bdd-style-test-runner-for-java-junit>

```java
import static com.google.common.truth.Truth.assertThat;
import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;

@RunWith(Spectrum.class)
public class SpectrumSumTest {
    {
        describe("Given two numbers a = 1 and b = 3", () -> {
            final int a = 1;
            final int b = 3;

            it("computing the sum of these 2 numbers, should compute sum = 4", () -> {
                final Sum sum = new Sum(a, b);

                assertThat(sum.getSum()).isEqualTo(4);
            });
        });
    }
}
```

#### Alternative: Oleaster 

* <https://github.com/mscharhag/oleaster>

### Hierarchies in JUnit: junit-hierarchicalcontextrunner

- <https://github.com/bechte/junit-hierarchicalcontextrunner>

```java
@RunWith(HierarchicalContextRunner.class)
public class HCRSumTest {

    public class GivenTwoNumbers1And3 {
        private int a = 1;
        private int b = 3;

        @Before
        public void setUp() {
            a = 1;
            b = 3;
        }

        public class WhenComputingSum {
            private Sum sum;

            @Before
            public void setUp() {
                sum = new Sum(a, b);
            }

            @Test
            public void thenShouldBeEqualTo4() throws ConcurrentException {
                assertThat(sum.getSum()).isEqualTo(4);
            }
        }

        public class WhenMultiplying {
            private int multiply;

            @Before
            public void setUp() {
                multiply = a * b;
            }

            @Test
            public void thenShouldBeEqualTo3() throws ConcurrentException {
                assertThat(multiply).isEqualTo(3);
            }
        }
    }
}
```

#### Novelty to consider: JUnit 5 Nested Tests

- <http://junit.org/junit5/docs/current/user-guide/#writing-tests-nested>
- The `@Nested` and `@DisplayName` annotations allow developers to reach an elegant "given/when/then" canvas

### BDD tools

#### Cucumber

- <https://cucumber.io/>

* Define the `.feature` file:

```gherkin
Feature: Sum computation

  Scenario Outline: Sum 2 integers
    Given two int <a> and <b> to sum
    When computing sum
    Then it should be <sum>

    Examples:
      |  a |  b | sum |
      |  1 |  3 |   4 |
      | -1 | -3 |  -4 |
      | -1 |  3 |   2 |
```

* Define the corresponding steps:

```java
public class SumSteps {
    Sum moSum;
    int miSum;

    @Given("^two int (-?\\d+) and (-?\\d+) to sum$")
    public void twoIntToSum(final int a, final int b) {
        moSum = new Sum(a, b);
    }

    @When("^computing sum$")
    public void computingSum() throws ConcurrentException {
        miSum = moSum.getSum();
    }

    @Then("^it should be (-?\\d+)$")
    public void itShouldBe(final int expected) {
        Assert.assertEquals(expected, miSum);
    }
}
```

* Define the specific runner:

```java
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/"
)
public class SumTestRunner {
}
```

* Relevant tools:
    * to write Gherkin features: [Tidy Gherkin](https://chrome.google.com/webstore/detail/tidy-gherkin/nobemmencanophcnicjhfhnjiimegjeo?hl=en-GB)
    * to display Gherkin features in Chrome a way pretty way: [Pretty Gherkin](https://chrome.google.com/webstore/detail/pretty-gherkin/blemhogdenfkkojlpghcinocbfjheioc?hl=en-GB)
    * to generating specifications from Gherkin source files: [featurebook](https://www.npmjs.com/package/featurebook)

#### JGiven

- <http://jgiven.org/>

```java
public class JGivenSumTest extends SimpleScenarioTest<JGivenSumTest.TestSteps> {

    @Test
    public void addition_isCorrect() throws ConcurrentException {
        given().first_number_$(1).and().second_number_$(3);
        when().computing_sum();
        then().it_should_be_$(4);
    }

    public static class TestSteps extends Stage<TestSteps> {
        private int mA;
        private int mB;
        private Sum mSum;

        public TestSteps first_number_$(final int piA) {
            mA = piA;
            return this;
        }

        public void second_number_$(final int piB) {
            mB = piB;
        }

        public void computing_sum() {
            mSum = new Sum(mA, mB);
        }

        public void it_should_be_$(final int piExpected) throws ConcurrentException {
            assertThat(mSum.getSum()).isEqualTo(piExpected);
        }
    }
}
```

### Mutation testing: Zester plugin

- <https://plugins.jetbrains.com/plugin/8281>
- <https://tech.zalando.com/blog/zester-mutation-testing/>

For this sample project, define a new "Run configuration" with Zester such as:

```
Target classes: com.guddy.android_testing_box.zester.*
Test class: com.guddy.android_testing_box.zester.ZesterExampleTest
```

It generates an HTML report in the `build/reports/zester/` directory, showing that 2 "mutants" survived to unit tests (so potential bugs, and in this case, yes it is).

### Alternative to JUnit: TestNG

* <http://testng.org/doc/index.html>

## Kotlin 

### Fluent assertions: Kluent

- <https://github.com/MarkusAmshove/Kluent>

#### Alternative: Expekt

* <https://github.com/winterbe/expekt>

### Specifications framework: Spek

- <https://github.com/JetBrains/spek>
- <http://jetbrains.github.io/spek/docs/latest/#setting-up>

```kotlin
@RunWith(JUnitPlatform::class)
class SpekSumTest : Spek({

    given("two numbers a = 1 and b = 3") {
        val a: Int = 1
        val b: Int = 3

        on("computing the sum of these 2 numbers") {
            val sum: Sum = Sum(a, b)

            it("should compute sum = 4") {
                sum.sum shouldBe 4
            }
        }
    }
})
```

## Android

### Fluent assertions: AssertJ Android

- <http://square.github.io/assertj-android/>

### Robotium

- <https://github.com/RobotiumTech/robotium>

```java
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    //region Rule
    @Rule
    public final ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);
    //endregion

    //region Fields
    private Solo mSolo;
    private MainActivity mActivity;
    private Context mContextTarget;
    //endregion

    //region Test lifecycle
    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
        mSolo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivity);
        mContextTarget = InstrumentationRegistry.getTargetContext();
    }

    @After
    public void tearDown() throws Exception {
        mSolo.finishOpenedActivities();
    }
    //endregion

    //region Test methods
    @Test
    public void testTextDisplayed() throws Exception {
        given("the main activity", () -> {

            when("launching activity", () -> {
                mActivity = mActivityTestRule.launchActivity(null);

                then("should display 'app_name'", () -> {
                    final boolean lbFoundAppName = mSolo.waitForText(mContextTarget.getString(R.string.app_name), 1, 5000L, true);
                    assertThat(lbFoundAppName);
                });
            });
        });
    }
    //endregion
}
```

### Espresso

- <https://google.github.io/android-testing-support-library/docs/espresso/>

### Robolectric

- <http://robolectric.org/>
- <http://robolectric.org/using-add-on-modules/> 

```groovy
    testCompile 'org.robolectric:robolectric:3.2.2'
    testCompile 'org.robolectric:shadows-multidex:3.2.2'
    testCompile 'org.robolectric:shadows-support-v4:3.2.2'
    testCompile 'org.khronos:opengl-api:gl1.1-android-2.1_r1'
```

```java
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RobolectricMainActivityTest {

    @Test
    public void test_clickingButton_shouldChangeText() throws Exception {

        given("The MainActivity", () -> {
            final MainActivity loActivity = Robolectric.setupActivity(MainActivity.class);
            final Button loButton = (Button) loActivity.findViewById(R.id.ActivityMain_Button);
            final TextView loTextView = (TextView) loActivity.findViewById(R.id.ActivityMain_TextView);

            when("clicking on the button", () -> {
                loButton.performClick();

                then("text should have changed", () -> assertThat(loTextView.getText().toString()).isEqualTo("Text changed after button click"));
            });
        });
    }

}
```

### Cucumber support

- <https://github.com/cucumber/cucumber-jvm/tree/master/examples/android>

* Configure the `build.gradle` file:

```groovy
android {
    defaultConfig {
        testApplicationId "com.guddy.android_testing_box.ui"
        testInstrumentationRunner "com.guddy.android_testing_box.ui.CucumberInstrumentationRunner"
    }
    
    sourceSets {
        androidTest {
            assets.srcDirs = ['src/androidTest/assets']
        }
    }
}
```

* Write features in the `src/androidTest/assets` directory, for example this `main.feature` file:

```gherkin
Feature: Main activity

  Scenario: Click on the button
    Given the initial state is shown
    When clicking on the button
    Then the text changed to "Text changed after button click"
```

* Define the corresponding steps:

```java
@CucumberOptions(features = "features")
public class CucumberMainActivitySteps extends ActivityInstrumentationTestCase2<MainActivity> {

    public CucumberMainActivitySteps() {
        super(MainActivity.class);
    }

    @Given("^the initial state is shown$")
    public void the_initial_main_activity_is_shown() {
        // Call the activity before each test.
        getActivity();
    }

    @When("^clicking on the button$")
    public void clicking_the_Click_Me_button() {
        onView(withId(R.id.ActivityMain_Button)).perform(click());
    }

    @Then("^the text changed to \"([^\"]*)\"$")
    public void text_$_is_shown(final String s) {
        onView(withId(R.id.ActivityMain_TextView)).check(matches(withText(s)));
    }
}
```

* Define the specific runner:

```java
public class CucumberInstrumentationRunner extends MonitoringInstrumentation {

    private final CucumberInstrumentationCore mInstrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        mInstrumentationCore.create(arguments);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();

        waitForIdleSync();
        mInstrumentationCore.start();
    }
}
```

### JGiven support

- <http://jgiven.org/userguide/#_android>
- <https://github.com/TNG/JGiven/tree/master/example-projects/android>

```java
@RunWith(AndroidJUnit4.class)
public class EspressoJGivenMainActivityTest extends
        SimpleScenarioTest<EspressoJGivenMainActivityTest.Steps> {

    @Rule
    @ScenarioState
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public AndroidJGivenTestRule androidJGivenTestRule = new AndroidJGivenTestRule(this.getScenario());

    @Test
    public void clicking_ClickMe_changes_the_text() {
        given().the_initial_main_activity_is_shown()
                .with().text("AndroidTestingBox");
        when().clicking_the_Click_Me_button();
        then().text_$_is_shown("Text changed after button click");
    }

    public static class Steps extends Stage<Steps> {
        @ScenarioState
        CurrentStep currentStep;

        @ScenarioState
        ActivityTestRule<MainActivity> activityTestRule;

        public Steps the_initial_main_activity_is_shown() {
            // nothing to do, just for reporting
            return this;
        }

        public Steps clicking_the_Click_Me_button() {
            onView(withId(R.id.ActivityMain_Button)).perform(click());
            return this;
        }

        public Steps text(@Quoted String s) {
            return text_$_is_shown(s);
        }

        public Steps text_$_is_shown(@Quoted String s) {
            onView(withId(R.id.ActivityMain_TextView)).check(matches(withText(s)));
            takeScreenshot();
            return this;
        }

        private void takeScreenshot() {
            currentStep.addAttachment(
                    Attachment.fromBinaryBytes(ScreenshotUtils.takeScreenshot(activityTestRule.getActivity()), MediaType.PNG)
                            .showDirectly());
        }
    }
}
```

## IDE configuration

- MoreUnit plugin:  <https://plugins.jetbrains.com/plugin/7105>

## Nota Bene

A relevant combination of [Dagger2](https://google.github.io/dagger/) and [mockito](http://site.mockito.org/) is already described in a previous post I wrote: <http://roroche.github.io/AndroidStarter/>

## Bibliography

- <https://blog.codecentric.de/en/2016/01/writing-better-tests-junit/>
- <https://www.petrikainulainen.net/programming/unit-testing/3-reasons-why-we-should-not-use-inheritance-in-our-tests/>
- <http://blog.xebia.com/mutation-testing-how-good-are-your-unit-tests/>

## Interesting repositories

- <https://github.com/googlesamples/android-testing>
- <https://github.com/TNG/JGiven/tree/master/jgiven-examples>
- <https://github.com/ahus1/bdd-examples>
- <https://github.com/chiuki/android-test-demo>

## Interesting articles

- <https://www.philosophicalhacker.com/post/some-resources-for-learning-how-to-test-android-apps/>
- <https://www.sitepoint.com/property-based-testing-with-javaslang/>
- <https://medium.com/@fabioCollini/android-testing-using-dagger-2-mockito-and-a-custom-junit-rule-c8487ed01b56>
- <https://offbeattesting.com/2017/04/13/unit-test/>

## Resources

- <https://www.petrikainulainen.net/writing-clean-tests/>
- <https://www.petrikainulainen.net/category/weekly/>

## Logo credits

Science graphic by <a href="http://www.flaticon.com/authors/pixel-perfect">Pixel perfect</a> from <a href="http://www.flaticon.com/">Flaticon</a> is licensed under <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0">CC BY 3.0</a>. Made with <a href="http://logomakr.com" title="Logo Maker">Logo Maker</a>
