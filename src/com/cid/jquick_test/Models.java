package com.cid.jquick_test;


import com.cid.jquick.annotations.JField;
import com.cid.jquick.annotations.JKey;
import com.cid.jquick.annotations.JObj;

public class Models {

    @JObj(alias = "name")
    public static class Name implements Testable<Name> {

        public Name() {
        }

        public Name(String first, String last, Name another) {
            this.first = first;
            this.last = last;
            this.another = another;
        }

        @JField(alias = "first")
        public String first;

        @JField(alias = "last")
        public String last;

        @JField(required = false, alias = "another")
        public Name another;

        @Override
        public void ensure_equal(Test of, Name other) throws RuntimeException {
            of.ensure(first.equals(other.first), "'first' == 'first'");
            of.ensure(last.equals(other.last), "'last' == 'last'");
            of.ensure((another == null) == (other.another == null), "Null Compare 'another'");
            if (another != null)
                another.ensure_equal(of, other.another);
        }
    }

    @JObj(alias = "person")
    public static class Person implements Testable<Person> {

        public Person() {
        }

        public Person(String name, int age, Person[] others) {
            this.name = name;
            this.age = age;
            this.others = others;
        }

        @JKey
        public String name;

        @JField(alias = "age")
        public int age;

        @JField(required = false, alias = "others")
        public Person[] others;

        @Override
        public void ensure_equal(Test of, Person other) throws RuntimeException {
            of.ensure(name.equals(other.name), "'name' == 'name'");
            of.ensure(age == other.age, "'age' == 'age'");
            of.ensure((others == null) == (other.others == null), "Null Compare 'others'");
            if (others != null)
            {
                of.ensure(others.length == other.others.length, "Size compare 'others'");
                for (int i = 0; i < others.length; i++) {
                    others[i].ensure_equal(of, other.others[i]);
                }
            }
        }
    }
}
