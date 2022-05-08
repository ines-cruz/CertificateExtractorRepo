# Project Description: 

The task is to create a REST endpoint which given a URL, fetches an SSL certificate information
(Subject, Issuer, Validity) and outputs it as JSON.

Optional tasks (ordered by importance):

• Tests are written for the endpoint

• The REST endpoint runs as a service in Docker (or similar)

• Results are stored in a DB (which is also dockerized)


# To Run: 

```bash
mvn jib:sockerBuild
```

```bash
docker-compose up  
```

# To Test:
Do a GET request with the following format: 
```bash
http://localhost:8080/?url={yoururl}
```

## Example:
```bash
http://localhost:8080/?url=https://www.cern.ch
```

### Result: 
```json
{ 
  "id":6, 
  "subject":"CN=www.cern.ch, O=CERN Organisation Européenne pour la Recherche Nucléaire, ST=Genève, C=CH",
  "issuer":"CN=Sectigo RSA Organization Validation Secure Server CA, O=Sectigo Limited, L=Salford, ST=Greater Manchester, C=GB",
  "valid":true
}
```

