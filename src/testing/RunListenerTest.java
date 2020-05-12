package testing;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import principal.Hotel;
import principal.Pago;
import principal.Reservar;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("unused")
@RunWith(MyRunner.class)
public class RunListenerTest {

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
	public void testComprobacionPago() throws java.lang.NullPointerException {
		try {
			assertEquals(true, pago.testComprobacionNumeroTarjetaYcvc("1234 1234 1234 1234", "123"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionPago_2() {
		try {
			assertEquals(false, pago.testComprobacionNumeroTarjetaYcvc("1234123412341234", "123"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionPago_3() {
		try {
			assertEquals(false, pago.testComprobacionNumeroTarjetaYcvc("1234 1234 1234 123j", "123"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionPago_4() {
		try {
			assertEquals(false, pago.testComprobacionNumeroTarjetaYcvc("1234 1234 1234 1234", "1j3"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionFechasEntradaYsalidaHotel() {
		try {
			assertEquals(false, hotel.testFechaEntradaSalida(-1, 0, -1, 0, -1, 0));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionFechasEntradaYsalidaHotel_2() {
		try {
			assertEquals(false, hotel.testFechaEntradaSalida(0, 1, 0, 1, 0, 1));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionFechasEntradaYsalidaHotel_3() {
		try {
			assertEquals(false, hotel.testFechaEntradaSalida(0, 0, 0, 0, 0, 0));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionFechasEntradaYsalidaHotel_4() {
		try {
			assertEquals(true, hotel.testFechaEntradaSalida(1, 1, 1, 1, 1, 1));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionFechasEntradaYsalidaHotel_5() {
		try {
			assertEquals(true, hotel.testFechaEntradaSalida(2020, 6, 28, 2020, 6, 30));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionFechasEntradaYsalidaHotel_6() {
		try {
			assertEquals(true, hotel.testFechaEntradaSalida(2020, 6, 28, 0, 0, 0));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionFechasEntradaYsalidaHotel_7() {
		try {
			assertEquals(true, hotel.testFechaEntradaSalida(0, 0, 0, 2020, 6, 28));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionSecundariaFechasHotel() {
		try {
			assertEquals(false, hotel.testFechasHotel(-1, 0, 0, 0, 0, 0));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionSecundariaFechasHotel_2() {
		try {
			assertEquals(false, hotel.testFechasHotel(1, 1, 1, 1, 1, 0));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionSecundariaFechasHotel_3() {
		try {
			assertEquals(false, hotel.testFechasHotel(0, 0, 0, 0, 0, 0));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionSecundariaFechasHotel_4() {
		try {
			assertEquals(true, hotel.testFechasHotel(2020, 6, 28, 2020, 6, 30));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testComprobacionSecundariaFechasHotel_5() {
		try {
			assertEquals(true, hotel.testFechasHotel(2020, 6, 28, 2021, 8, 30));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
