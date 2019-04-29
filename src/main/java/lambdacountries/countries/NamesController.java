package lambdacountries.countries;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class NamesController
{
	// localhost:8080/names/all
	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllCountries()
	{
		CountriesApplication.ourCountryList.countryList.sort
				(
					(e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName())
				);
		return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList, HttpStatus.OK);

	}

	// localhost:8080/names/*letter*
	@GetMapping(value = "/start/{letter}")
	public ResponseEntity<?> getCountryLetter(@PathVariable char letter)
	{
		ArrayList<Country> filteredCountries =
				CountriesApplication.ourCountryList.findCountries(c ->
						c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
		filteredCountries.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));

		return new ResponseEntity<>(filteredCountries, HttpStatus.OK);
	}

	// localhost:8080/names/size/*number*
	@GetMapping(value = "/size/{number}")
	public ResponseEntity<?> byCountrySize(@PathVariable int number)
	{
		ArrayList<Country> filteredCountries =
				CountriesApplication.ourCountryList.findCountries(c ->
						c.getName().length() > number
				);
		filteredCountries.sort((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()));
		return new ResponseEntity<>(filteredCountries, HttpStatus.OK);
	}

}
