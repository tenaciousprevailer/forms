package com.tenacious.forms;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.tenacious.forms");

        noClasses()
            .that()
                .resideInAnyPackage("com.tenacious.forms.service..")
            .or()
                .resideInAnyPackage("com.tenacious.forms.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.tenacious.forms.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
