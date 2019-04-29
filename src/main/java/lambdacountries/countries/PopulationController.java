package lambdacountries.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;

@RestController
@RequestMapping("/populations")
public class PopulationController
{
	// localhost:8080/populations/size/{people}
	@GetMapping(value = "/size/{people}")
	public ResponseEntity<?>  getCountrySize(@PathVariable int people)
	{
		ArrayList<Country> filteredCountries =
				CountriesApplication.ourCountryList.findCountries(c ->
						c.getPopulation() >= people
				);
		return new ResponseEntity<>(filteredCountries, HttpStatus.OK);
	}

	//localhost:8080/populations/min
	@GetMapping(value = "/min")
	public ResponseEntity<?> leastPopulation()
	{
		CountryList countries = CountriesApplication.ourCountryList;
		countries.countryList.sort((e1, e2) -> e1.getPopulation() - e2.getPopulation());
		return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
	}

	//localhost:8080/populations/min
	@GetMapping(value = "/max")
	public ResponseEntity<?> mostPopulation()
	{
		CountryList countries = CountriesApplication.ourCountryList;
		countries.countryList.sort((e1, e2) -> e2.getPopulation() - e1.getPopulation());
		return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
	}

	//localhost:8080/populations/median
	@GetMapping(value = "/median")
	public ResponseEntity<?> medianPopulation()
	{
		CountryList countries = CountriesApplication.ourCountryList;
		countries.countryList.sort(Comparator.comparingInt(Country::getPopulation));


		return new ResponseEntity<>(countries.countryList.get(countries.countryList.size() / 2), HttpStatus.OK);

	}
}
