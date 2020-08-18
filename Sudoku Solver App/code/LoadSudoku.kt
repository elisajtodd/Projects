package com.example.games

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_load_sudoku.*
import kotlin.random.Random


class LoadSudoku : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_sudoku)

        newSudoku(newsdk)
    }
    var sud = Array(9) {IntArray(9) {0} }

    //makes a sudoku puzzle
    fun newSudoku(view: View){
        //sudoku values
        sud = Array(9) {IntArray(9) {0} }

        val dir = arrayOf(aa, ab, ac, ad, ae, af, ag, ah, ai, ba, bb, bc, bd, be, bf, bg, bh, bi,
            ca, cb, cc, cd, ce, cf, cg, ch, ci, da, db, dc, dd, de, df, dg, dh, di,
            ea, eb, ec, ed, ee, ef, eg, eh, ei, fa, fb, fc, fd, fe, ff, fg, fh, fi,
            ga, gb, gc, gd, ge, gf, gg, gh, gi, ha, hb, hc, hd, he, hf, hg, hh, hi,
            ia, ib, ic, id, ie, iff, ig, ih, ii)

        var cnt = 0
        while (cnt < 13){
            val y = Random.nextInt(0,9)
            val x = Random.nextInt(0,9)
            val a = Random.nextInt(1,10)
            if(isValid(y,x,a)) {
                sud[y][x] = a
                cnt++
            }
        }

        var d = 0   //iterate through dir
        //assign value in cells
        for (i in 0 until 9){
            for (j in 0 until 9){
                if(sud[i][j]!=0) {
                    dir[d].text = sud[i][j].toString() //replaces values to the number
                    dir[d].setTextColor(resources.getColor(R.color.colorPrimary))
                }else{
                    dir[d].text = getString(R.string.blank)
                    dir[d].setTextColor(resources.getColor(R.color.textColor))
                }
                d++ //next in dir
            }
        }
    }

    private fun isValid(y: Int, x: Int, num: Int): Boolean{
        val ys = (y/3)*3
        val xs = (x/3)*3
        var cnt = 0
        for(i in  0 until 3) {
            for(j in 0 until 3) {
                if ((sud[i+ys][j+xs] == num) or (sud[y][cnt] == num) or (sud[cnt][x] == num)) return false
                cnt++
            }
        }
        return true
    }

    //fill sudoku with solution
    fun solve(view: View){
        //sudoku values
        //*var sud = Array(9) {IntArray(9) {0} }
        var solvable = solver(0,0, 405)

        val dir = arrayOf(aa, ab, ac, ad, ae, af, ag, ah, ai, ba, bb, bc, bd, be, bf, bg, bh, bi,
            ca, cb, cc, cd, ce, cf, cg, ch, ci, da, db, dc, dd, de, df, dg, dh, di,
            ea, eb, ec, ed, ee, ef, eg, eh, ei, fa, fb, fc, fd, fe, ff, fg, fh, fi,
            ga, gb, gc, gd, ge, gf, gg, gh, gi, ha, hb, hc, hd, he, hf, hg, hh, hi,
            ia, ib, ic, id, ie, iff, ig, ih, ii)

        var d = 0   //iterate through dir
        //assign value in cells
        if (solvable) {
            for (i in 0 until sud.size) {
                for (j in 0 until sud[0].size) {
                    dir[d].text = sud[i][j].toString() //replaces values to the number
                    d++ //next in dir
                }
            }
        }else{
            newSudoku(newsdk)
            solve(solve_btn)
        }
    }

    private fun solver(y:Int, x:Int, n:Int): Boolean{
        var z:Int
        var w:Int
        if (n == 0) return true
        if ((n < 0) or (y == 9)){
            return false
        }
        if(sud[y][x] == 0) {
            for (i in 1..9) {
                if (isValid(y, x, i)) {
                    sud[y][x] = i
                    if (x + 1 > 8) {
                        z = 0
                        w = y + 1
                    } else {
                        z = x + 1
                        w = y
                    }
                    val a = solver(w, z, n - i)
                    if (a) return true
                    sud[y][x] = 0
                }
            }
            return false
        }
        val b = sud[y][x]
        if (x + 1 > 8) {
            z = 0
            w = y + 1
        } else {
            z = x + 1
            w = y
        }
        return solver(w, z, n-b)
    }

    //reset sudoku with first values
    fun reset(view: View){

        val dir = arrayOf(aa, ab, ac, ad, ae, af, ag, ah, ai, ba, bb, bc, bd, be, bf, bg, bh, bi,
            ca, cb, cc, cd, ce, cf, cg, ch, ci, da, db, dc, dd, de, df, dg, dh, di,
            ea, eb, ec, ed, ee, ef, eg, eh, ei, fa, fb, fc, fd, fe, ff, fg, fh, fi,
            ga, gb, gc, gd, ge, gf, gg, gh, gi, ha, hb, hc, hd, he, hf, hg, hh, hi,
            ia, ib, ic, id, ie, iff, ig, ih, ii)

        var cnt = 0
        for (d in dir){
            if (d.currentTextColor != (resources.getColor(R.color.colorPrimary))) d.text = getString(R.string.blank)
        }
    }

}
