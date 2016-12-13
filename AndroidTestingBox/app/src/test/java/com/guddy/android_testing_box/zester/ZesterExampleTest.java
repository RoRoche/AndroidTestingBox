package com.guddy.android_testing_box.zester;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZesterExampleTest {
    //region Fields
    private NameParser mNameParser;
    private String[] mNames;
    //endregion

    //region Lifecycle
    @Before
    public void setUp() {
        mNameParser = new NameParser();
        mNames = new String[]{"Mike Jones", "John Doe"};
    }
    //endregion

    //region Test methods
    @Test
    public void shouldFindPersonByLastName() {
        final Person person = mNameParser.findPersonWithLastName(mNames, "Doe");
        final String firstName = person.getFirstName();
        assertEquals("John", firstName);
    }
    //endregion
}