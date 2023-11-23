package eu.qwan.vendingmachine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;



public class VendingMachineTest {
    VendingMachine machine = new VendingMachine();
    @Nested
    class Delivery {
        @BeforeEach
        void setUpMachine() {
            machine.configureChoice(Choice.FIZZY_ORANGE, Can.FANTA);
            machine.configureChoice(Choice.COLA, Can.COKE);
        }
        @Test
        void deliversNothingOnIllegalChoice() {
            assertThat(machine.deliver(Choice.BEER), is(equalTo(Can.NO_CAN)));
        }
        @Test
        void deliversCokeOnColaChoice() {
            assertThat(machine.deliver(Choice.COLA), is(equalTo(Can.COKE)));
        }
        @Test
        void deliversFantaOnFizzyOrangeChoice() {
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.FANTA)));
        }
    }
    @Nested
    class PricedDelivery {
        @BeforeEach
        void setUpMachine() {
            machine.configureChoice(Choice.FIZZY_ORANGE, Can.FANTA, 100);
        }
        @Test
        void deliversNothingWhenNotPaid() {
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.NO_CAN)));
        }
        @Test
        void deliversWhenNotPaidEnough() {
            machine.insert(100);
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.FANTA)));
        }
        @Test
        void deliversWhenPaidTooMuch() {
            machine.insert(200);
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.FANTA)));
        }
        @Test
        void deliversOnAddedPayments() {
            machine.insert(50);
            machine.insert(50);
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.FANTA)));
        }
        @Test
        void doesNotDeliveryIndefinitely() {
            machine.insert(100);
            machine.deliver(Choice.FIZZY_ORANGE);
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.NO_CAN)));
        }
        @Test
        void deliversMoreWhenCreditsAllow() {
            machine.insert(200);
            machine.deliver(Choice.FIZZY_ORANGE);
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.FANTA)));
        }
        @Test
        void usesPriceOfChoiceToCheckout() {
            machine.configureChoice(Choice.COLA, Can.COKE, 200);
            machine.insert(100);
            assertThat(machine.deliver(Choice.FIZZY_ORANGE), is(equalTo(Can.FANTA)));
        }
    }
}
