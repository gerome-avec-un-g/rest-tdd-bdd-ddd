package fr.geromeavecung.library;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchUnitTest {

    public static final JavaClasses CLASSES = new ClassFileImporter().importPackages("fr.geromeavecung.library");

    @Test
    void domain_cant_depend_on_drivers() {
        ArchRule rule = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..drivers..");

        rule.check(CLASSES);
    }

    @Test
    void domain_cant_depend_on_clients() {
        ArchRule rule = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..clients..");

        rule.check(CLASSES);
    }

    @Test
    void clients_cant_depend_on_drivers() {
        ArchRule rule = noClasses().that().resideInAPackage("..clients..")
                .should().dependOnClassesThat().resideInAPackage("..drivers..");

        rule.check(CLASSES);
    }

    @Test
    void bounded_contexts_cant_depend_on_use_cases() {
        ArchRule rule = noClasses().that().resideInAPackage("..boundedcontexts..")
                .should().dependOnClassesThat().resideInAPackage("..usecases..");

        rule.check(CLASSES);
    }

    @Test
    void bounded_context_authors_cant_depend_on_books() {
        ArchRule rule = noClasses().that().resideInAPackage("..boundedcontexts.authors..")
                .should().dependOnClassesThat().resideInAPackage("..boundedcontexts.books..");

        rule.check(CLASSES);
    }

    @Test
    void bounded_context_books_cant_depend_on_authors() {
        ArchRule rule = noClasses().that().resideInAPackage("..boundedcontexts.books..")
                .should().dependOnClassesThat().resideInAPackage("..boundedcontexts.authors..");

        rule.check(CLASSES);
    }

    // TODO use cases can't depend on DDD repository

    @Test
    void cucumber_steps_annotations_cant_be_and() {
        ArchRule rule = noClasses().should().dependOnClassesThat()
                .haveFullyQualifiedName("io.cucumber.java.en.And");

        rule.check(CLASSES);
    }

}