/*
* Name: Nirajan Pandey
* Course: MET CS 473
* Instructor: Dave Hendrickson
* TA: Karen Palmer
*
* The program is a MIPS disassembler, which is a 32-bit architecture for CPU.
* It takes hexadecimal number as input and converts it into Assembly instruction.
 */


public class Main {

    public static void main(String[] args) {

        // Hex instructions.
        int[] instruction = new int[]{0x032BA020, 0x8CE90014, 0x12A90003, 0x022DA822, 0xADB30020,
                0x02697824, 0xAE8FFFF4, 0x018C6020, 0x02A4A825, 0x0158FFFF7, 0x8ECDFFF0};

        // Initial Memory Address
        int address = 0x9A03C;


        // This for loop, loops the hex instructions compares it with opcodes
        // of the instruction in MIPS architecture, figures out the R-Format
        // or I-Format and performs necessary action to disassemble Hex
        // instruction Assembly and print out.
        for (int i = 0; i < instruction.length; i++) {

            // Variable name for R-Format.
            address = address + 4;
            int opCode = ((instruction[i] & 0xFC000000) >>> 26);
            int src1 = ((instruction[i] & 0x03E00000) >>> 21);
            int src2 = ((instruction[i] & 0x001F0000) >>> 16);
            int destR = ((instruction[i] & 0x0000F800) >>> 11);
            int func = ((instruction[i] & 0x0000003F));

            // Variable for I-Format.
            int srcI = ((instruction[i] & 0x03E00000) >>> 21);
            int srcOrDestI = ((instruction[i] & 0x001F0000) >>> 16);

            int offSet = 0;

            if (opCode == 0x23) {
                offSet = (((byte) instruction[i] & (byte) 0x000000FF));
            } else if (opCode == 0x2B) {
                offSet = (((byte) instruction[i] & (byte) 0x000000FF));
            } else if (opCode == 0x04) {
                offSet = (((byte) instruction[i] & (byte) 0x00000003) << 2);

            } else if (opCode == 0x05) {
                offSet = (((byte) instruction[i] & (byte) 0x000000FF) <<2);

            }

            System.out.print(String.format("%05X ",address ));
            System.out.print("\t " );
            if (opCode == 0x0) {
                if (func == 0x20) {
                    System.out.println("add " + "$" + destR + ", $" + src1 + ", $" + src2);
                }
                if (func == 0x22) {
                    System.out.println("sub " + "$" + destR + ", $" + src1 + ", $" + src2);
                }
                if (func == 0x24) {
                    System.out.println("and " + "$" + destR + ", $" + src1 + ", $" + src2);
                }
                if (func == 0x25) {
                    System.out.println("or  " + "$" + destR + ", $" + src1 + ", $" + src2);
                }
            } else if (opCode == 0x23) {
                System.out.println( "lw  " + "$" + srcOrDestI + ", "+  offSet + "($" + srcI + ")");
            } else if (opCode == 0x04) {
                System.out.print("beq " + "$" + srcOrDestI + ", $" + srcI );
                System.out.print("\t\t address " );
                System.out.println(String.format("%05X ",address + 16 ));

            } else if (opCode == 0x2B) {
                System.out.println("sw  " + "$"+ srcOrDestI + ", " + offSet +"($" + srcI + ")");
            } else if (opCode == 0x05) {
                System.out.print("bne " + "$" + srcOrDestI + " "  + "($"+ srcI + ")");
                System.out.print("\t\t address " );
                System.out.println(String.format("%05X ",address -32 ));
            }
        }
    }
}
