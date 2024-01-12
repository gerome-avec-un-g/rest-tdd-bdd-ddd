package fr.geromeavecung.resttddbddddd.resttddbddddd;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchUnitTest {
    
    @Test
    void cucumber_steps_annotations_cannot_be_and() {
        JavaClasses classes = new ClassFileImporter().importPackages("fr.geromeavecung.explorationgame");

        ArchRule rule = noClasses().should().dependOnClassesThat()
                .haveFullyQualifiedName("io.cucumber.java.en.And");
    
        rule.check(classes);
    }

}