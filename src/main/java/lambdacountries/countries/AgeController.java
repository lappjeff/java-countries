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
@RequestMapping(value = "/ages")
public class AgeController
{
	//localhost:8080/ages/age/{age}
	@GetMapping(value = "age/{age}")
	public ResponseEntity<?> greaterThanMedianAge(@PathVariable int age)
	{
		ArrayList<Country> filteredCountries =
				CountriesApplication.ourCountryList.findCountries(c -> c.getMedianAge() >= age);

		return new ResponseEntity<>(filteredCountries, HttpStatus.OK);
	}

	//localhost:8080/ages/min
	@GetMapping(value = "/min")
	public ResponseEntity<?> smallestMedianAge()
	{
		CountryList countries = CountriesApplication.ourCountryList;
		countries.countryList.sort(Comparator.comparingInt(Country::getMedianAge));

		return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
	}

	//localhost:8080/ages/max
	@GetMapping(value = "/max")
	public ResponseEntity<?> largestMedianAge()
	{
		CountryList countries = CountriesApplication.ourCountryList;
		countries.countryList.sort((c1, c2) -> c2.getMedianAge() - c1.getMedianAge());

		return new ResponseEntity<>(countries.countryList.get(0), HttpStatus.OK);
	}
}
