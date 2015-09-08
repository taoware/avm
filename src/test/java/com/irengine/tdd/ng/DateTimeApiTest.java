package com.irengine.tdd.ng;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/*
 * JSR 310
 * human view and machine view
 * 
 */
public class DateTimeApiTest {

	@Test
	public void testMachineTime() {
		System.out.printf("EPOCH = %s%n", Instant.EPOCH);
		System.out.printf("MAX = %s%n", Instant.MAX);
		System.out.printf("MIN = %s%n", Instant.MIN);

		System.out.printf("Now = %s%n", Instant.now());
		System.out.printf("50 seconds past epoch = %s%n", Instant.ofEpochSecond(50));
		System.out.printf("Parsed = %s%n", Instant.parse("2007-12-03T10:15:30Z"));

		System.out.printf("ZERO = %s%n", Duration.ZERO);

		System.out.printf("30-second duration = %s%n", Duration.ofSeconds(30));
		System.out.printf("Parsed = %s%n", Duration.parse("PT3M20S"));
	}

	@Test
	public void testMachineTimeGetter() {
		Instant now = Instant.now();
		System.out.printf("Now = %s%n", now);
		System.out.printf("Epoch second = %d%n", now.getEpochSecond());
		System.out.printf("Nano = %d%n", now.getNano());
		System.out.printf("After epoch = %b%n", now.isAfter(Instant.EPOCH));
		System.out.printf("Before epoch = %b%n", now.isBefore(Instant.EPOCH));

		Duration dur = Duration.ofSeconds(49L - (long) (Math.random() * 80));
		System.out.printf("Nano = %d%n", dur.getNano());
		System.out.printf("Seconds = %d%n", dur.getSeconds());
		System.out.printf("Is negative = %b%n", dur.isNegative());
		System.out.printf("Is zero = %b%n", dur.isZero());
	}

	@Test
	public void testMachineTimeInstance() {
		Instant now = Instant.now();
		System.out.printf("Now = %s%n", now);
		System.out.printf("Now-20 seconds = %s%n", now.minusSeconds(20));
		System.out.printf("Now+500,000,000 nanoseconds = %s%n", now.plusNanos(500000000));

		Duration dur = Duration.ofDays(10);
		System.out.printf("Dur = %s%n", dur);
		System.out.printf("Dur/2 = %s%n", dur.dividedBy(2));
		System.out.printf("Dur-3 days = %s%n", dur.minusDays(3));
		System.out.printf("Dur*2 = %s%n", dur.multipliedBy(2));
		System.out.printf("Dur+12 hours = %s%n", dur.plusHours(12));
		System.out.printf("Dur nanos set to 500,000,000 = %s%n", dur.withNanos(500000000));
		System.out.printf("Dur seconds set to 3600 = %s%n", dur.withSeconds(3600));
	}

	@Test
	public void testHumanTime() {
		// create from current date
		LocalDate localDate = LocalDate.now();
		System.out.printf("Local date = %s%n", localDate);

		// create from specific values
		localDate = LocalDate.of(2012, 12, 21);
		System.out.printf("Local date = %s%n", localDate);

		// create from 100 days into 1970
		localDate = LocalDate.ofEpochDay(100);
		System.out.printf("Local date = %s%n", localDate);

		// create from another local date
		localDate = LocalDate.from(localDate);
		System.out.printf("Local date = %s%n%n", localDate);

		// create from current time
		LocalTime localTime = LocalTime.now();
		System.out.printf("Local time = %s%n", localTime);

		// create from specific values
		localTime = LocalTime.of(9, 15);
		System.out.printf("Local time = %s%n", localTime);

		// create from 120 seconds past midnight
		localTime = LocalTime.ofSecondOfDay(120);
		System.out.printf("Local time = %s%n", localTime);

		// create from another local time
		localTime = LocalTime.from(localTime);
		System.out.printf("Local time = %s%n%n", localTime);

		// create from current date and time
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.printf("Local date and time = %s%n", localDateTime);
	}

	@Test
	public void testHumanTimeGetter() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.printf("Date-time: %s%n", localDateTime);
		System.out.printf("Day of year: %d%n", localDateTime.getDayOfYear());
		LocalDate localDate = localDateTime.toLocalDate();
		LocalTime localTime = localDateTime.toLocalTime();
		System.out.printf("Date: %02d-%02d-%02d%n", localDate.getYear(), localDate.getMonthValue(),
				localDate.getDayOfMonth());
		System.out.printf("Time: %02d:%02d:%-2d%n", localTime.getHour(), localTime.getMinute(), localTime.getSecond());
	}

	@Test
	public void testHumanTimeInstance() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.printf("Date-time: %s%n", localDateTime);
		System.out.printf("Date-time: %s%n", localDateTime.withYear(2012));
		System.out.printf("Date-time: %s%n", localDateTime.minusWeeks(3));
		System.out.printf("Date-time: %s%n", localDateTime.with(TemporalAdjusters.lastDayOfMonth()));
		LocalDate localDate = LocalDate.of(2010, 12, 1);
		LocalTime localTime = LocalTime.of(10, 15);
		localDateTime = localDateTime.with(localDate).with(localTime);
		System.out.printf("Date-time: %s%n", localDateTime);
	}

	@Test
	public void testHumanTimeWithZone() {
		ZoneId zid = ZoneId.systemDefault();
		System.out.printf("Zone Id = %s%n", zid);
		System.out.printf("Rules = %s%n", zid.getRules());
		System.out.printf("DST in effect: %b%n", zid.getRules().isDaylightSavings(Instant.now()));

		zid = ZoneId.of("Europe/Paris");
		System.out.printf("Zone Id = %s%n", zid);

		ZoneOffset zoffset = ZoneOffset.of("+06:00");
		System.out.printf("Zone Offset = %s%n", zoffset);
		System.out.printf("Total seconds = %d%n", zoffset.getTotalSeconds());

		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.printf("Zoned date and time = %s%n", zonedDateTime);
		System.out.printf("Zone = %s%n", zonedDateTime.getZone());

		zoffset = ZoneOffset.from(zonedDateTime);
		System.out.printf("Zone Offset = %s%n", zoffset);

		OffsetDateTime offsetDateTime = OffsetDateTime.now();
		System.out.printf("Offset date and time = %s%n", offsetDateTime);
		System.out.printf("Offset date and time = %s%n", offsetDateTime.with(TemporalAdjusters.lastDayOfMonth()));

		zonedDateTime = ZonedDateTime.of(2013, 11, 2, 3, 00, 0, 0, ZoneId.of("America/Chicago"));
		System.out.printf("Zoned date and time = %s%n", zonedDateTime);

		zonedDateTime = ZonedDateTime.of(2013, 11, 3, 3, 00, 0, 0, ZoneId.of("America/Chicago"));
		System.out.printf("Zoned date and time = %s%n", zonedDateTime);

		offsetDateTime = OffsetDateTime.of(2013, 11, 2, 3, 00, 0, 0, zoffset);
		System.out.printf("Offset date and time = %s%n", offsetDateTime);

		offsetDateTime = OffsetDateTime.of(2013, 11, 3, 3, 00, 0, 0, zoffset);
		System.out.printf("Offset date and time = %s%n", offsetDateTime);
	}
	
	@Test
	public void testCustomizeAdjuster() {
		
	}

}
