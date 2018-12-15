object Dependencies {

  fun jgiven(module: String, version: String = Versions.Test.jgiven) =
    "com.tngtech.jgiven:jgiven-$module:$version"

  fun archunit(module: String, version: String = Versions.Test.archunit) =
    "com.tngtech.archunit:archunit-$module:$version"

  fun jupiter(module: String) = "org.junit.jupiter:junit-jupiter-$module"

  fun axon(module: String, version: String = Versions.axon) =
    "org.axonframework:axon-$module:$version"
}
