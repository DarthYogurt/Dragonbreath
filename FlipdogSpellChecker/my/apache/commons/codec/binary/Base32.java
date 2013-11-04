package my.apache.commons.codec.binary;

public class Base32 extends BaseNCodec
{
  private static final int BITS_PER_ENCODED_BYTE = 5;
  private static final int BYTES_PER_ENCODED_BLOCK = 8;
  private static final int BYTES_PER_UNENCODED_BLOCK = 5;
  private static final byte[] CHUNK_SEPARATOR = { 13, 10 };
  private static final byte[] DECODE_TABLE;
  private static final byte[] ENCODE_TABLE;
  private static final byte[] HEX_DECODE_TABLE = arrayOfByte2;
  private static final byte[] HEX_ENCODE_TABLE = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86 };
  private static final int MASK_5BITS = 31;
  private long bitWorkArea;
  private final int decodeSize;
  private final byte[] decodeTable;
  private final int encodeSize;
  private final byte[] encodeTable;
  private final byte[] lineSeparator;

  static
  {
    byte[] arrayOfByte1 = new byte[91];
    arrayOfByte1[0] = -1;
    arrayOfByte1[1] = -1;
    arrayOfByte1[2] = -1;
    arrayOfByte1[3] = -1;
    arrayOfByte1[4] = -1;
    arrayOfByte1[5] = -1;
    arrayOfByte1[6] = -1;
    arrayOfByte1[7] = -1;
    arrayOfByte1[8] = -1;
    arrayOfByte1[9] = -1;
    arrayOfByte1[10] = -1;
    arrayOfByte1[11] = -1;
    arrayOfByte1[12] = -1;
    arrayOfByte1[13] = -1;
    arrayOfByte1[14] = -1;
    arrayOfByte1[15] = -1;
    arrayOfByte1[16] = -1;
    arrayOfByte1[17] = -1;
    arrayOfByte1[18] = -1;
    arrayOfByte1[19] = -1;
    arrayOfByte1[20] = -1;
    arrayOfByte1[21] = -1;
    arrayOfByte1[22] = -1;
    arrayOfByte1[23] = -1;
    arrayOfByte1[24] = -1;
    arrayOfByte1[25] = -1;
    arrayOfByte1[26] = -1;
    arrayOfByte1[27] = -1;
    arrayOfByte1[28] = -1;
    arrayOfByte1[29] = -1;
    arrayOfByte1[30] = -1;
    arrayOfByte1[31] = -1;
    arrayOfByte1[32] = -1;
    arrayOfByte1[33] = -1;
    arrayOfByte1[34] = -1;
    arrayOfByte1[35] = -1;
    arrayOfByte1[36] = -1;
    arrayOfByte1[37] = -1;
    arrayOfByte1[38] = -1;
    arrayOfByte1[39] = -1;
    arrayOfByte1[40] = -1;
    arrayOfByte1[41] = -1;
    arrayOfByte1[42] = -1;
    arrayOfByte1[43] = -1;
    arrayOfByte1[44] = -1;
    arrayOfByte1[45] = -1;
    arrayOfByte1[46] = -1;
    arrayOfByte1[47] = 63;
    arrayOfByte1[48] = -1;
    arrayOfByte1[49] = -1;
    arrayOfByte1[50] = 26;
    arrayOfByte1[51] = 27;
    arrayOfByte1[52] = 28;
    arrayOfByte1[53] = 29;
    arrayOfByte1[54] = 30;
    arrayOfByte1[55] = 31;
    arrayOfByte1[56] = -1;
    arrayOfByte1[57] = -1;
    arrayOfByte1[58] = -1;
    arrayOfByte1[59] = -1;
    arrayOfByte1[60] = -1;
    arrayOfByte1[61] = -1;
    arrayOfByte1[62] = -1;
    arrayOfByte1[63] = -1;
    arrayOfByte1[64] = -1;
    arrayOfByte1[66] = 1;
    arrayOfByte1[67] = 2;
    arrayOfByte1[68] = 3;
    arrayOfByte1[69] = 4;
    arrayOfByte1[70] = 5;
    arrayOfByte1[71] = 6;
    arrayOfByte1[72] = 7;
    arrayOfByte1[73] = 8;
    arrayOfByte1[74] = 9;
    arrayOfByte1[75] = 10;
    arrayOfByte1[76] = 11;
    arrayOfByte1[77] = 12;
    arrayOfByte1[78] = 13;
    arrayOfByte1[79] = 14;
    arrayOfByte1[80] = 15;
    arrayOfByte1[81] = 16;
    arrayOfByte1[82] = 17;
    arrayOfByte1[83] = 18;
    arrayOfByte1[84] = 19;
    arrayOfByte1[85] = 20;
    arrayOfByte1[86] = 21;
    arrayOfByte1[87] = 22;
    arrayOfByte1[88] = 23;
    arrayOfByte1[89] = 24;
    arrayOfByte1[90] = 25;
    DECODE_TABLE = arrayOfByte1;
    ENCODE_TABLE = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 50, 51, 52, 53, 54, 55 };
    byte[] arrayOfByte2 = new byte[88];
    arrayOfByte2[0] = -1;
    arrayOfByte2[1] = -1;
    arrayOfByte2[2] = -1;
    arrayOfByte2[3] = -1;
    arrayOfByte2[4] = -1;
    arrayOfByte2[5] = -1;
    arrayOfByte2[6] = -1;
    arrayOfByte2[7] = -1;
    arrayOfByte2[8] = -1;
    arrayOfByte2[9] = -1;
    arrayOfByte2[10] = -1;
    arrayOfByte2[11] = -1;
    arrayOfByte2[12] = -1;
    arrayOfByte2[13] = -1;
    arrayOfByte2[14] = -1;
    arrayOfByte2[15] = -1;
    arrayOfByte2[16] = -1;
    arrayOfByte2[17] = -1;
    arrayOfByte2[18] = -1;
    arrayOfByte2[19] = -1;
    arrayOfByte2[20] = -1;
    arrayOfByte2[21] = -1;
    arrayOfByte2[22] = -1;
    arrayOfByte2[23] = -1;
    arrayOfByte2[24] = -1;
    arrayOfByte2[25] = -1;
    arrayOfByte2[26] = -1;
    arrayOfByte2[27] = -1;
    arrayOfByte2[28] = -1;
    arrayOfByte2[29] = -1;
    arrayOfByte2[30] = -1;
    arrayOfByte2[31] = -1;
    arrayOfByte2[32] = -1;
    arrayOfByte2[33] = -1;
    arrayOfByte2[34] = -1;
    arrayOfByte2[35] = -1;
    arrayOfByte2[36] = -1;
    arrayOfByte2[37] = -1;
    arrayOfByte2[38] = -1;
    arrayOfByte2[39] = -1;
    arrayOfByte2[40] = -1;
    arrayOfByte2[41] = -1;
    arrayOfByte2[42] = -1;
    arrayOfByte2[43] = -1;
    arrayOfByte2[44] = -1;
    arrayOfByte2[45] = -1;
    arrayOfByte2[46] = -1;
    arrayOfByte2[47] = 63;
    arrayOfByte2[49] = 1;
    arrayOfByte2[50] = 2;
    arrayOfByte2[51] = 3;
    arrayOfByte2[52] = 4;
    arrayOfByte2[53] = 5;
    arrayOfByte2[54] = 6;
    arrayOfByte2[55] = 7;
    arrayOfByte2[56] = 8;
    arrayOfByte2[57] = 9;
    arrayOfByte2[58] = -1;
    arrayOfByte2[59] = -1;
    arrayOfByte2[60] = -1;
    arrayOfByte2[61] = -1;
    arrayOfByte2[62] = -1;
    arrayOfByte2[63] = -1;
    arrayOfByte2[64] = -1;
    arrayOfByte2[65] = 10;
    arrayOfByte2[66] = 11;
    arrayOfByte2[67] = 12;
    arrayOfByte2[68] = 13;
    arrayOfByte2[69] = 14;
    arrayOfByte2[70] = 15;
    arrayOfByte2[71] = 16;
    arrayOfByte2[72] = 17;
    arrayOfByte2[73] = 18;
    arrayOfByte2[74] = 19;
    arrayOfByte2[75] = 20;
    arrayOfByte2[76] = 21;
    arrayOfByte2[77] = 22;
    arrayOfByte2[78] = 23;
    arrayOfByte2[79] = 24;
    arrayOfByte2[80] = 25;
    arrayOfByte2[81] = 26;
    arrayOfByte2[82] = 27;
    arrayOfByte2[83] = 28;
    arrayOfByte2[84] = 29;
    arrayOfByte2[85] = 30;
    arrayOfByte2[86] = 31;
    arrayOfByte2[87] = 32;
  }

  public Base32()
  {
    this(false);
  }

  public Base32(int paramInt)
  {
    this(paramInt, CHUNK_SEPARATOR);
  }

  public Base32(int paramInt, byte[] paramArrayOfByte)
  {
    this(paramInt, paramArrayOfByte, false);
  }

  public Base32(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
  }

  public Base32(boolean paramBoolean)
  {
    this(0, null, paramBoolean);
  }

  void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.eof)
      return;
    if (paramInt2 < 0)
      this.eof = true;
    int i = 0;
    int k;
    for (int j = paramInt1; ; j = k)
    {
      if (i >= paramInt2);
      int m;
      while ((this.eof) && (this.modulus >= 2))
      {
        ensureBufferSize(this.decodeSize);
        switch (this.modulus)
        {
        default:
          return;
        case 2:
          byte[] arrayOfByte19 = this.buffer;
          int i19 = this.pos;
          this.pos = (i19 + 1);
          arrayOfByte19[i19] = ((byte)(int)(0xFF & this.bitWorkArea >> 2));
          return;
          k = j + 1;
          m = paramArrayOfByte[j];
          if (m != 61)
            break label162;
          this.eof = true;
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
      }
      label162: ensureBufferSize(this.decodeSize);
      if ((m >= 0) && (m < this.decodeTable.length))
      {
        int n = this.decodeTable[m];
        if (n >= 0)
        {
          this.modulus = ((1 + this.modulus) % 8);
          this.bitWorkArea = ((this.bitWorkArea << 5) + n);
          if (this.modulus == 0)
          {
            byte[] arrayOfByte1 = this.buffer;
            int i1 = this.pos;
            this.pos = (i1 + 1);
            arrayOfByte1[i1] = ((byte)(int)(0xFF & this.bitWorkArea >> 32));
            byte[] arrayOfByte2 = this.buffer;
            int i2 = this.pos;
            this.pos = (i2 + 1);
            arrayOfByte2[i2] = ((byte)(int)(0xFF & this.bitWorkArea >> 24));
            byte[] arrayOfByte3 = this.buffer;
            int i3 = this.pos;
            this.pos = (i3 + 1);
            arrayOfByte3[i3] = ((byte)(int)(0xFF & this.bitWorkArea >> 16));
            byte[] arrayOfByte4 = this.buffer;
            int i4 = this.pos;
            this.pos = (i4 + 1);
            arrayOfByte4[i4] = ((byte)(int)(0xFF & this.bitWorkArea >> 8));
            byte[] arrayOfByte5 = this.buffer;
            int i5 = this.pos;
            this.pos = (i5 + 1);
            arrayOfByte5[i5] = ((byte)(int)(0xFF & this.bitWorkArea));
          }
        }
      }
      i++;
    }
    byte[] arrayOfByte18 = this.buffer;
    int i18 = this.pos;
    this.pos = (i18 + 1);
    arrayOfByte18[i18] = ((byte)(int)(0xFF & this.bitWorkArea >> 7));
    return;
    this.bitWorkArea >>= 4;
    byte[] arrayOfByte16 = this.buffer;
    int i16 = this.pos;
    this.pos = (i16 + 1);
    arrayOfByte16[i16] = ((byte)(int)(0xFF & this.bitWorkArea >> 8));
    byte[] arrayOfByte17 = this.buffer;
    int i17 = this.pos;
    this.pos = (i17 + 1);
    arrayOfByte17[i17] = ((byte)(int)(0xFF & this.bitWorkArea));
    return;
    this.bitWorkArea >>= 1;
    byte[] arrayOfByte13 = this.buffer;
    int i13 = this.pos;
    this.pos = (i13 + 1);
    arrayOfByte13[i13] = ((byte)(int)(0xFF & this.bitWorkArea >> 16));
    byte[] arrayOfByte14 = this.buffer;
    int i14 = this.pos;
    this.pos = (i14 + 1);
    arrayOfByte14[i14] = ((byte)(int)(0xFF & this.bitWorkArea >> 8));
    byte[] arrayOfByte15 = this.buffer;
    int i15 = this.pos;
    this.pos = (i15 + 1);
    arrayOfByte15[i15] = ((byte)(int)(0xFF & this.bitWorkArea));
    return;
    this.bitWorkArea >>= 6;
    byte[] arrayOfByte10 = this.buffer;
    int i10 = this.pos;
    this.pos = (i10 + 1);
    arrayOfByte10[i10] = ((byte)(int)(0xFF & this.bitWorkArea >> 16));
    byte[] arrayOfByte11 = this.buffer;
    int i11 = this.pos;
    this.pos = (i11 + 1);
    arrayOfByte11[i11] = ((byte)(int)(0xFF & this.bitWorkArea >> 8));
    byte[] arrayOfByte12 = this.buffer;
    int i12 = this.pos;
    this.pos = (i12 + 1);
    arrayOfByte12[i12] = ((byte)(int)(0xFF & this.bitWorkArea));
    return;
    this.bitWorkArea >>= 3;
    byte[] arrayOfByte6 = this.buffer;
    int i6 = this.pos;
    this.pos = (i6 + 1);
    arrayOfByte6[i6] = ((byte)(int)(0xFF & this.bitWorkArea >> 24));
    byte[] arrayOfByte7 = this.buffer;
    int i7 = this.pos;
    this.pos = (i7 + 1);
    arrayOfByte7[i7] = ((byte)(int)(0xFF & this.bitWorkArea >> 16));
    byte[] arrayOfByte8 = this.buffer;
    int i8 = this.pos;
    this.pos = (i8 + 1);
    arrayOfByte8[i8] = ((byte)(int)(0xFF & this.bitWorkArea >> 8));
    byte[] arrayOfByte9 = this.buffer;
    int i9 = this.pos;
    this.pos = (i9 + 1);
    arrayOfByte9[i9] = ((byte)(int)(0xFF & this.bitWorkArea));
  }

  void encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.eof);
    do
    {
      return;
      if (paramInt2 >= 0)
        break;
      this.eof = true;
    }
    while ((this.modulus == 0) && (this.lineLength == 0));
    ensureBufferSize(this.encodeSize);
    int i8 = this.pos;
    switch (this.modulus)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      this.currentLinePos += this.pos - i8;
      if ((this.lineLength <= 0) || (this.currentLinePos <= 0))
        break;
      System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
      this.pos += this.lineSeparator.length;
      return;
      byte[] arrayOfByte33 = this.buffer;
      int i33 = this.pos;
      this.pos = (i33 + 1);
      arrayOfByte33[i33] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 3))];
      byte[] arrayOfByte34 = this.buffer;
      int i34 = this.pos;
      this.pos = (i34 + 1);
      arrayOfByte34[i34] = this.encodeTable[(0x1F & (int)(this.bitWorkArea << 2))];
      byte[] arrayOfByte35 = this.buffer;
      int i35 = this.pos;
      this.pos = (i35 + 1);
      arrayOfByte35[i35] = 61;
      byte[] arrayOfByte36 = this.buffer;
      int i36 = this.pos;
      this.pos = (i36 + 1);
      arrayOfByte36[i36] = 61;
      byte[] arrayOfByte37 = this.buffer;
      int i37 = this.pos;
      this.pos = (i37 + 1);
      arrayOfByte37[i37] = 61;
      byte[] arrayOfByte38 = this.buffer;
      int i38 = this.pos;
      this.pos = (i38 + 1);
      arrayOfByte38[i38] = 61;
      byte[] arrayOfByte39 = this.buffer;
      int i39 = this.pos;
      this.pos = (i39 + 1);
      arrayOfByte39[i39] = 61;
      byte[] arrayOfByte40 = this.buffer;
      int i40 = this.pos;
      this.pos = (i40 + 1);
      arrayOfByte40[i40] = 61;
      continue;
      byte[] arrayOfByte25 = this.buffer;
      int i25 = this.pos;
      this.pos = (i25 + 1);
      arrayOfByte25[i25] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 11))];
      byte[] arrayOfByte26 = this.buffer;
      int i26 = this.pos;
      this.pos = (i26 + 1);
      arrayOfByte26[i26] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 6))];
      byte[] arrayOfByte27 = this.buffer;
      int i27 = this.pos;
      this.pos = (i27 + 1);
      arrayOfByte27[i27] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 1))];
      byte[] arrayOfByte28 = this.buffer;
      int i28 = this.pos;
      this.pos = (i28 + 1);
      arrayOfByte28[i28] = this.encodeTable[(0x1F & (int)(this.bitWorkArea << 4))];
      byte[] arrayOfByte29 = this.buffer;
      int i29 = this.pos;
      this.pos = (i29 + 1);
      arrayOfByte29[i29] = 61;
      byte[] arrayOfByte30 = this.buffer;
      int i30 = this.pos;
      this.pos = (i30 + 1);
      arrayOfByte30[i30] = 61;
      byte[] arrayOfByte31 = this.buffer;
      int i31 = this.pos;
      this.pos = (i31 + 1);
      arrayOfByte31[i31] = 61;
      byte[] arrayOfByte32 = this.buffer;
      int i32 = this.pos;
      this.pos = (i32 + 1);
      arrayOfByte32[i32] = 61;
      continue;
      byte[] arrayOfByte17 = this.buffer;
      int i17 = this.pos;
      this.pos = (i17 + 1);
      arrayOfByte17[i17] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 19))];
      byte[] arrayOfByte18 = this.buffer;
      int i18 = this.pos;
      this.pos = (i18 + 1);
      arrayOfByte18[i18] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 14))];
      byte[] arrayOfByte19 = this.buffer;
      int i19 = this.pos;
      this.pos = (i19 + 1);
      arrayOfByte19[i19] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 9))];
      byte[] arrayOfByte20 = this.buffer;
      int i20 = this.pos;
      this.pos = (i20 + 1);
      arrayOfByte20[i20] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 4))];
      byte[] arrayOfByte21 = this.buffer;
      int i21 = this.pos;
      this.pos = (i21 + 1);
      arrayOfByte21[i21] = this.encodeTable[(0x1F & (int)(this.bitWorkArea << 1))];
      byte[] arrayOfByte22 = this.buffer;
      int i22 = this.pos;
      this.pos = (i22 + 1);
      arrayOfByte22[i22] = 61;
      byte[] arrayOfByte23 = this.buffer;
      int i23 = this.pos;
      this.pos = (i23 + 1);
      arrayOfByte23[i23] = 61;
      byte[] arrayOfByte24 = this.buffer;
      int i24 = this.pos;
      this.pos = (i24 + 1);
      arrayOfByte24[i24] = 61;
      continue;
      byte[] arrayOfByte9 = this.buffer;
      int i9 = this.pos;
      this.pos = (i9 + 1);
      arrayOfByte9[i9] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 27))];
      byte[] arrayOfByte10 = this.buffer;
      int i10 = this.pos;
      this.pos = (i10 + 1);
      arrayOfByte10[i10] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 22))];
      byte[] arrayOfByte11 = this.buffer;
      int i11 = this.pos;
      this.pos = (i11 + 1);
      arrayOfByte11[i11] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 17))];
      byte[] arrayOfByte12 = this.buffer;
      int i12 = this.pos;
      this.pos = (i12 + 1);
      arrayOfByte12[i12] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 12))];
      byte[] arrayOfByte13 = this.buffer;
      int i13 = this.pos;
      this.pos = (i13 + 1);
      arrayOfByte13[i13] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 7))];
      byte[] arrayOfByte14 = this.buffer;
      int i14 = this.pos;
      this.pos = (i14 + 1);
      arrayOfByte14[i14] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 2))];
      byte[] arrayOfByte15 = this.buffer;
      int i15 = this.pos;
      this.pos = (i15 + 1);
      arrayOfByte15[i15] = this.encodeTable[(0x1F & (int)(this.bitWorkArea << 3))];
      byte[] arrayOfByte16 = this.buffer;
      int i16 = this.pos;
      this.pos = (i16 + 1);
      arrayOfByte16[i16] = 61;
    }
    int i = 0;
    int k;
    for (int j = paramInt1; ; j = k)
    {
      if (i >= paramInt2)
        return;
      ensureBufferSize(this.encodeSize);
      this.modulus = ((1 + this.modulus) % 5);
      k = j + 1;
      int m = paramArrayOfByte[j];
      if (m < 0)
        m += 256;
      this.bitWorkArea = ((this.bitWorkArea << 8) + m);
      if (this.modulus == 0)
      {
        byte[] arrayOfByte1 = this.buffer;
        int n = this.pos;
        this.pos = (n + 1);
        arrayOfByte1[n] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 35))];
        byte[] arrayOfByte2 = this.buffer;
        int i1 = this.pos;
        this.pos = (i1 + 1);
        arrayOfByte2[i1] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 30))];
        byte[] arrayOfByte3 = this.buffer;
        int i2 = this.pos;
        this.pos = (i2 + 1);
        arrayOfByte3[i2] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 25))];
        byte[] arrayOfByte4 = this.buffer;
        int i3 = this.pos;
        this.pos = (i3 + 1);
        arrayOfByte4[i3] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 20))];
        byte[] arrayOfByte5 = this.buffer;
        int i4 = this.pos;
        this.pos = (i4 + 1);
        arrayOfByte5[i4] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 15))];
        byte[] arrayOfByte6 = this.buffer;
        int i5 = this.pos;
        this.pos = (i5 + 1);
        arrayOfByte6[i5] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 10))];
        byte[] arrayOfByte7 = this.buffer;
        int i6 = this.pos;
        this.pos = (i6 + 1);
        arrayOfByte7[i6] = this.encodeTable[(0x1F & (int)(this.bitWorkArea >> 5))];
        byte[] arrayOfByte8 = this.buffer;
        int i7 = this.pos;
        this.pos = (i7 + 1);
        arrayOfByte8[i7] = this.encodeTable[(0x1F & (int)this.bitWorkArea)];
        this.currentLinePos = (8 + this.currentLinePos);
        if ((this.lineLength > 0) && (this.lineLength <= this.currentLinePos))
        {
          System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
          this.pos += this.lineSeparator.length;
          this.currentLinePos = 0;
        }
      }
      i++;
    }
  }

  public boolean isInAlphabet(byte paramByte)
  {
    return (paramByte >= 0) && (paramByte < this.decodeTable.length) && (this.decodeTable[paramByte] != -1);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.commons.codec.binary.Base32
 * JD-Core Version:    0.6.2
 */