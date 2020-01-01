package sakila.inventory.model;

public class Film {
	private int filmId;
	private String title;
	private String decription;
	private String year;
	private String languageId;
	private String originalLanguage;
	private String rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	public String getOriginalLanguage() {
		return originalLanguage;
	}
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}
	public String getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(String rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getReplacementCost() {
		return replacementCost;
	}
	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", decription=" + decription + ", year=" + year
				+ ", languageId=" + languageId + ", originalLanguage=" + originalLanguage + ", rentalDuration="
				+ rentalDuration + ", rentalRate=" + rentalRate + ", length=" + length + ", replacementCost="
				+ replacementCost + ", rating=" + rating + ", specialFeatures=" + specialFeatures + "]";
	}
	
	
}
