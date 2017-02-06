
##] create a file client-swagger-codegen-jersey2-config-catalog.json with contents:
{
  "modelPackage":"org.openo.sdno.lcm.restclient.catalog.model",
  "apiPackage":"org.openo.sdno.lcm.restclient.catalog.api",
  "groupId":"org.openo.sdno.lcm",
  "artifactId":"restclient-catalog",
  "artifactVersion":"1.1.0-SNAPSHOT",
  "library":"jersey2",
  "dateLibrary":"java8"
}

### GENERATE THE REST CLIENT CODE FOR CATALOG ###
java -jar swagger-codegen-2.2.0/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -i catalog-master-swagger.json -l java -o sdno-lcm-restclient-catalog -c client-swagger-codegen-jersey2-config-catalog.json

### need to remove the android and other unused code
