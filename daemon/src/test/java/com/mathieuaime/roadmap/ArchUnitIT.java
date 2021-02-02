package com.mathieuaime.roadmap;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.Test;

public class ArchUnitIT {
  @Test
  void architecture() {
    JavaClasses jc = new ClassFileImporter().importPackages("com.mathieuaime.roadmap");

    LayeredArchitecture arch = layeredArchitecture()
        // Define layers
        .layer("Config").definedBy("..config..")
        .layer("Persistence").definedBy("..persistence..")
        .layer("Web").definedBy("..web..")
        .layer("Domain").definedBy("..service..", "..model..", "..repository..")
        // Add constraints
        .whereLayer("Config").mayNotBeAccessedByAnyLayer()
        .whereLayer("Persistence").mayNotBeAccessedByAnyLayer()
        .whereLayer("Web").mayNotBeAccessedByAnyLayer()
        .whereLayer("Domain").mayOnlyBeAccessedByLayers("Web", "Persistence", "Config");

    arch.check(jc);
  }
}
