class Matrix {
	private int raws, cols;
	private Complex[][] data;
	
	public Matrix (int raws, int cols) {
		this.raws = raws;
		this.cols = cols;
		this.data = new Complex[raws][cols];
        for (int i = 0; i < raws; i++)
        	for (int j = 0; j < cols; j++)
        		data[i][j] = new Complex(0.0, 0.0);
	}
	
	public int getRaws() {
		return raws;
	}
	
	public int getCols() {
		return cols;
	}
	
	public Complex getData(int i, int j) {
		return data[i][j];
	}
	
	public void setData(int i, int j, Complex digit) {
		data[i][j] = digit;
	}
	
	public Matrix addition(Matrix other) {
		if (this.raws != other.raws || this.cols != other.cols) {
			throw new IllegalArgumentException ("Разные по размерам матрицы нельзя складывать");
		}
		Matrix result = new Matrix (raws, cols);
		for (int i = 0; i < raws; i++)
			for (int j = 0; j < cols; j++)
				result.data[i][j] = this.data[i][j].addition(other.data[i][j]);
		return result;
	}
	
	public Matrix subtraction(Matrix other) {
		if (this.raws != other.raws || this.cols != other.cols) {
			throw new IllegalArgumentException ("Разные по размерам матрицы нельзя вычитать");
		}
		Matrix result = new Matrix(raws, cols);
		for (int i = 0; i < raws; i++)
			for (int j = 0; j < cols; j++)
				result.data[i][j] = this.data[i][j].subtraction(other.data[i][j]);
		return result;
	}
	
	public Matrix multiplication(Matrix other) {
		if ( this.cols != other.raws) {
			throw new IllegalArgumentException ("Количество столбцов первой матрицы должно быть равно количеству строк во второй !!!");
		}
		Matrix result = new Matrix (this.raws, other.cols);
		for (int i = 0; i < raws; i++)
			for (int j = 0; j < other.cols; j++) {
				Complex summ = new Complex (0.0, 0.0);
		        for (int k = 0; k < cols; k++)
		        	summ = summ.addition(this.data[i][k].multiplication(other.data[k][j]));
		        result.data[i][j] = summ;
			}
		return result;		        	
	}
	
	private Complex determinant_count(Complex [][] a, int n) {
	    if (n == 1) return a[0][0];
	    if (n == 2) return a[0][0].multiplication(a[1][1]).subtraction(a[0][1].multiplication(a[1][0]));
	    Complex result = new Complex(0, 0);
	    for (int col = 0; col < n; col++) {
	        Complex[][] minor = new Complex[n - 1][n - 1];
	        for (int i = 1; i < n; i++) {
	            int aj = 0;
	            for (int j = 0; j < n; j++) {
	                if (j == col) continue;
	                minor[i - 1][aj++] = a[i][j];
	            }
	        }
	        Complex term = a[0][col].multiplication(determinant_count(minor, n - 1));
	        if (col % 2 == 0) result = result.addition(term);
	        else              result = result.subtraction(term);
	    }
	    return result;
	}
	
	public Complex determinant() {
		if ( raws != cols) {
			throw new IllegalArgumentException("Матрица не квадратная");
		}
		return determinant_count(data, raws);
	}
	
	public Matrix division(Matrix other) {
		if (other.raws != other.cols) {
			throw new IllegalArgumentException("Нельзя разделить на матрицу, т.к. она не квадратная");
		}
		Complex det = other.determinant();
		if (det.getRE() == 0 && det.getIM() == 0) {
			throw new IllegalArgumentException ("Детерминант равен 0, обратной матрицы для деления не существует");
		}
		Matrix inversion = new Matrix (other.raws, other.raws);
		for (int i = 0; i < other.raws; i++) {
			for (int j = 0; j < other.raws; j++) {
				Complex[][] minor = new Complex [other.raws - 1][other.raws - 1];
				int mi = 0;
				for (int r = 0; r < other.raws; r++) {
					if (r == j) {
						continue;
					}
					int mj = 0;
					for (int a = 0; a < other.raws; a++) {
						if (a == i) {
							continue;
						}
						minor[mi][mj++] = other.data[r][a];
					}
					mi++;
				}
				Complex coef = other.determinant_count(minor, other.raws - 1);
				if ((i + j) % 2 != 0) {
					coef = new Complex(-coef.getRE(), -coef.getIM());
				}
				inversion.data[i][j] = coef.division(det);
			}
		}
		return this.multiplication(inversion);
	}
	
	public Matrix transposition() {
	    Matrix result = new Matrix(cols, raws);
	    for (int i = 0; i < raws; i++)
	        for (int j = 0; j < cols; j++)
	            result.data[j][i] = this.data[i][j];
	    return result;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < raws; i++) {
			for (int j = 0; j < cols; j++)
				sb.append(data[i][j]).append("\t");
			sb.append("\n");
		}
		return sb.toString();
	}
}