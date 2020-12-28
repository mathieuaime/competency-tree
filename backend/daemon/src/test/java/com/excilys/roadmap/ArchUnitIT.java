package com.excilys.roadmap;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.Test;

public class ArchUnitIT {
  @Test
  void architecture() {
    JavaClasses jc = new ClassFileImporter().importPackages("com.excilys.roadmap");

    LayeredArchitecture arch = layeredArchitecture()
        // Define layers
        .layer("Web").definedBy("..web..")
        .layer("Domain").definedBy("..service..", "..model..", "..repository..")
        .layer("Persistence").definedBy("..persistence..")
        .layer("Config").definedBy("..config..")
        // Add constraints
        .whereLayer("Web").mayNotBeAccessedByAnyLayer()
        .whereLayer("Persistence").mayNotBeAccessedByAnyLayer()
        .whereLayer("Config").mayNotBeAccessedByAnyLayer()
        .whereLayer("Domain").mayOnlyBeAccessedByLayers("Web", "Persistence", "Config");

    arch.check(jc);
  }
}
