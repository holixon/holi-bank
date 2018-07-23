package de.holisticon.bank


import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.axonframework.spring.stereotype.Aggregate

/**
 * Architectural tests with [archUnit](https://www.archunit.org/userguide/html/000_Index.html).
 *
 * Work in progress, still finding out if this is useful.
 *
 */
@AnalyzeClasses(packages = ["de.holisticon.bank"])
class ArchUnitTest {

  companion object {
    @ArchTest
    @JvmField
    val `all aggregates are regular classes` = classes()
      .that()
      .areAnnotatedWith(Aggregate::class.java)
      .should()
      .notBeInterfaces()
  }
}
