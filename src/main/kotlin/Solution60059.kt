// not passed
class Solution60059 {
    class Matrix {
        val matrix: Array<BooleanArray>
        val size: Int

        constructor(matrix: Array<BooleanArray>) {
            this.matrix = matrix
            this.size = matrix.size
        }

        constructor(matrix: Array<IntArray>) {
            this.matrix = matrix.map { rows ->
                rows.map { it == 1 }.toBooleanArray()
            }.toTypedArray()
            this.size = matrix.size
        }

        override fun equals(other: Any?): Boolean {
            if (other is Matrix) {
                return this.hashCode() == other.hashCode()
            }
            return false
        }

        override fun hashCode(): Int {
            return this.matrix.contentDeepHashCode()
        }

        fun get(row: Int, col: Int): Boolean {
            return matrix[row][col]
        }

        fun changeMatrixSize(newSize: Int): Matrix {
            val curSize: Int = this.size
            val halfDiff: Int = (newSize - curSize) / 2
            val newMatrix = Array(newSize) { BooleanArray(newSize) }
            for (i in 0 until newSize) {
                for (j in 0 until newSize) {
                    if (i < size && j < size) {
                        newMatrix[i + halfDiff][j + halfDiff] = matrix[i][j]
                    }
                }
            }
            return Matrix(newMatrix)
        }

        fun rotate(angle: Int): Matrix {
            val resultMat = Array(matrix.size) { BooleanArray(matrix.size) }
            when (angle) {
                0 -> {
                    return this
                }

                1 -> {
                    for (i in matrix.indices) {
                        for (j in matrix.indices) {
                            resultMat[j][size - i - 1] = matrix[i][j]
                        }
                    }
                }

                2 -> {
                    for (i in matrix.indices) {
                        for (j in matrix.indices) {
                            resultMat[size - i - 1][size - j - 1] = matrix[i][j]
                        }
                    }
                }

                3 -> {
                    for (i in matrix.indices) {
                        for (j in matrix.indices) {
                            resultMat[size - j - 1][i] = matrix[i][j]
                        }
                    }
                }
            }
            return Matrix(resultMat)
        }

        fun move(direction: Int): Matrix {
            val newMatrix = Array(matrix.size) { BooleanArray(matrix.size) }
            when (direction) {
                0 -> {
                    for (i in 0 until matrix.size - 1) newMatrix[i] = matrix[i + 1].copyOf()
                }

                1 -> {
                    for (i in matrix.indices) {
                        for (j in 1 until matrix.size) {
                            newMatrix[i][j] = matrix[i][j - 1]
                        }
                    }
                }

                2 -> {
                    for (i in 1 until matrix.size) newMatrix[i] = matrix[i - 1].copyOf()
                }

                3 -> {
                    for (i in matrix.indices) {
                        for (j in 0 until matrix.size - 1) {
                            newMatrix[i][j] = matrix[i][j + 1]
                        }
                    }
                }
            }
            return Matrix(newMatrix)
        }

        fun canUnlock(lock: Matrix): Boolean {
            var keyHoleCount = 0
            for (i in matrix.indices) {
                for (j in matrix.indices) {
                    val keyHole = this.get(i, j)
                    val lockHole = lock.get(i, j)
                    keyHoleCount += 1
                    if (!keyHole.xor(lockHole)) {
                        return false
                    }
                }
            }
            return keyHoleCount > 0
        }
    }

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val newSize = lock.size
        val keyMatrix = Matrix(key).changeMatrixSize(newSize)
        val lockMatrix = Matrix(lock).changeMatrixSize(newSize)
        val visitedSet = mutableSetOf<Matrix>()

        fun dfs(matrix: Matrix): Boolean {
            if (visitedSet.contains(matrix)) {
                return false
            }
            visitedSet.add(matrix)

            if (matrix.canUnlock(lockMatrix)) {
                return true
            }

            for (i in 0 until 4) {
                val moveMatrix = matrix.move(i)
                if (!visitedSet.contains(moveMatrix)) {
                    if (dfs(moveMatrix)) {
                        visitedSet.add(matrix)
                        return true
                    }
                }
                val rotateMatrix = matrix.rotate(i)
                if (!visitedSet.contains(rotateMatrix)) {
                    if (dfs(rotateMatrix)) {
                        visitedSet.add(matrix)
                        return true
                    }
                }
            }
            return false
        }

        return dfs(keyMatrix)
    }
}