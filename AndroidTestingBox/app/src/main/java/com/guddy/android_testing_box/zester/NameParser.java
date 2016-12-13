package com.guddy.android_testing_box.zester;

public class NameParser {
    public Person findPersonWithLastName(final String[] names, final String lastNameToFind) {
        Person result = null;
        for (int i = 0; i <= names.length; i++) { // bug 1
            final String[] parts = names[i].split(" ");
            final String firstName = parts[0];
            final String lastName = parts[1];
            if (lastName.equals(lastNameToFind)) {
                result = new Person(firstName, lastName);
                break;
            }
        }
        return result;
    }
}
