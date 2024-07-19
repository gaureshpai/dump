#include <LPC214x.H> 

__irq void Ext_ISR(void);

int main (void) {
    EXTMODE = 0x4; //Edge sensitive mode on EINT2
    EXTPOLAR &= ~(0x4); //Falling Edge Sensitive
    PINSEL0 = 0x0000C000; //Select Pin function P0.15 as EINT2
    /* initialize the interrupt vector */
    VICIntSelect &= ~ (1<<16); // EINT2 selected as IRQ 16
    VICVectAddr5 = (unsigned int)Ext_ISR; // address of the ISR
    VICVectCntl5 = (1<<5) | 16; // 
    VICIntEnable = (1<<16); // EINT2 interrupt enabled
    EXTINT &= ~(0x4);
    while (1) 
    { }
}

__irq void Ext_ISR(void) // Interrupt Service Routine-ISR 
{
IO1DIR |=0xFF000000;//make Port P1.31 to P1.24 as output
IO1PIN ^= 0x01000000; // Turn ON Buzzer
EXTINT |= 0x4; //clear interrupt
VICVectAddr = 0; // End of interrupt execution
}
