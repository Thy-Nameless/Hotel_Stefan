package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionListener;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import principal.Hotel;
import principal.Pago;
import principal.Reservar;

/*Borja*/

@SuppressWarnings("unused")
class JUnitTest {

	Reservar reserva;
	Pago pago;
	Hotel hotel;

	@BeforeEach
	void setUp() throws Exception {
		reserva = new Reservar();
		pago = new Pago();
		hotel = new Hotel();
	}

	@Test
	void testComprobacionPago() {
		assertEquals(true, pago.testComprobacionNumeroTarjetaYcvc("1234 1234 1234 1234", "123"));
	}

	@Test
	void testComprobacionPago_2() {
		assertEquals(false, pago.testComprobacionNumeroTarjetaYcvc("1234123412341234", "123"));
	}

	@Test
	void testComprobacionPago_3() {
		assertEquals(false, pago.testComprobacionNumeroTarjetaYcvc("1234 1234 1234 123j", "123"));
	}

	@Test
	void testComprobacionPago_4() {
		assertEquals(false, pago.testComprobacionNumeroTarjetaYcvc("1234 1234 1234 1234", "1j3"));
	}
	
	@Test
	void testComprobacionFechasEntradaYsalidaHotel() {
		assertEquals(false, hotel.testFechaEntradaSalida(-1, 0, -1, 0, -1, 0));
	}
	
	@Test
	void testComprobacionFechasEntradaYsalidaHotel_2() {
		assertEquals(false, hotel.testFechaEntradaSalida(0, 1, 0, 1, 0, 1));
	}
	
	@Test
	void testComprobacionFechasEntradaYsalidaHotel_3() {
		assertEquals(false, hotel.testFechaEntradaSalida(0, 0, 0, 0, 0, 0));
	}
	
	@Test
	void testComprobacionFechasEntradaYsalidaHotel_4() {
		assertEquals(true, hotel.testFechaEntradaSalida(1, 1, 1, 1, 1, 1));
	}
	
	@Test
	void testComprobacionFechasEntradaYsalidaHotel_5() {
		assertEquals(true, hotel.testFechaEntradaSalida(2020, 6, 28, 2020, 6, 30));
	}
	
	@Test
	void testComprobacionFechasEntradaYsalidaHotel_6() {
		assertEquals(true, hotel.testFechaEntradaSalida(2020, 6, 28, 0, 0, 0));
	}
	
	@Test
	void testComprobacionFechasEntradaYsalidaHotel_7() {
		assertEquals(true, hotel.testFechaEntradaSalida(0, 0, 0, 2020, 6, 28));
	}
	
	@Test
	void testComprobacionSecundariaFechasHotel() {
		assertEquals(false, hotel.testFechasHotel(-1, 0, 0, 0, 0, 0));
	}
	
	@Test
	void testComprobacionSecundariaFechasHotel_2() {
		assertEquals(false, hotel.testFechasHotel(1, 1, 1, 1, 1, 0));
	}
	
	@Test
	void testComprobacionSecundariaFechasHotel_3() {
		assertEquals(false, hotel.testFechasHotel(0, 0, 0, 0, 0, 0));
	}
	
	@Test
	void testComprobacionSecundariaFechasHotel_4() {
		assertEquals(true, hotel.testFechasHotel(2020, 6, 28, 2020, 6, 30));
	}
	
	@Test
	void testComprobacionSecundariaFechasHotel_5() {
		assertEquals(true, hotel.testFechasHotel(2020, 6, 28, 2021, 8, 30));
	}
}
