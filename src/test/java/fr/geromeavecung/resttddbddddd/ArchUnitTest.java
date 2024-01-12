package fr.geromeavecung.resttddbddddd;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchUnitTest {
    
    @Test
    void domain_cant_depend_on_drivers() {
        JavaClasses classes = new ClassFileImporter().importPackages("fr.geromeavecung.resttddbddddd");

        ArchRule rule = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..drivers..");
    
        rule.check(classes);
    }

    @Test
    void domain_cant_depend_on_clients() {
        JavaClasses classes = new ClassFileImporter().importPackages("fr.geromeavecung.resttddbddddd");

        ArchRule rule = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..clients..");

        rule.check(classes);
    }

    @Test
    void clients_cant_depend_on_drivers() {
        JavaClasses classes = new ClassFileImporter().importPackages("fr.geromeavecung.resttddbddddd");

        ArchRule rule = noClasses().that().resideInAPackage("..clients..")
                .should().dependOnClassesThat().resideInAPackage("..drivers..");

        rule.check(classes);
    }

    @Test
    void bounded_contexts_cant_depend_on_use_cases() {
        JavaClasses classes = new ClassFileImporter().importPackages("fr.geromeavecung.resttddbddddd");

        ArchRule rule = noClasses().that().resideInAPackage("..boundedcontexts..")
                .should().dependOnClassesThat().resideInAPackage("..usecases..");

        rule.check(classes);
    }

    // TODO use cases can't depend on DDD repository
    @Test
    void cucumber_steps_annotations_cannot_be_and() {
        JavaClasses classes = new ClassFileImporter().importPackages("fr.geromeavecung.resttddbddddd");

        ArchRule rule = noClasses().should().dependOnClassesThat()
                .haveFullyQualifiedName("io.cucumber.java.en.And");

        rule.check(classes);
    }


}