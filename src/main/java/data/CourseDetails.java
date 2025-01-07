package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class CourseDetails {
    private final String title;
    private final String rawDate;
    private final LocalDate formattedDate;
    private final String url;

    public CourseDetails(String title, String rawDate, String url) {
        this.title = title;
        this.rawDate = rawDate;
        this.formattedDate = parseDate(rawDate);
        this.url = url;
    }

    public CourseDetails(String title, String rawDate, LocalDate formattedDate) {
        this.title = title;
        this.rawDate = rawDate;
        this.formattedDate = formattedDate;
        this.url = null;
    }

    private LocalDate parseDate(String rawDate) {
        int separatorIndex = rawDate.indexOf(" ·");
        if (separatorIndex == -1) {
            throw new IllegalArgumentException("Invalid date format: " + rawDate);
        }
        String datePart = rawDate.substring(0, separatorIndex);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy", new Locale("ru"));
        return LocalDate.parse(datePart, formatter);
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getFormattedDate() {
        return formattedDate;
    }

    public String getRawDate() {
        return rawDate;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseDetails other = (CourseDetails) obj;
        return Objects.equals(title, other.title) &&
                Objects.equals(formattedDate, other.formattedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, formattedDate);
    }

    @Override
    public String toString() {
        return String.format("CourseDetails[title='%s', date='%s', url='%s']",
                title, formattedDate, url);
    }
}
