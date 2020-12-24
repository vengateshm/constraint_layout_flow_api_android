package com.vengateshm.teamlineupview.teamLineUpView

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import com.vengateshm.teamlineupview.databinding.TeamViewBinding

class TeamView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding = TeamViewBinding
        .inflate(LayoutInflater.from(context), this, true)
    private val memberViews: MutableList<View> = mutableListOf()

    init {
        binding.teamWrapper.background = HalfFieldDrawable(context)
        radius = 8.toFloat()
    }

    fun bind(teamColor: Int, members: List<Member>) {
        memberViews.forEach {
            binding.teamWrapper.removeView(it)
        }
        memberViews.clear()
        binding.apply {
            teamShirt.imageTintList = ColorStateList.valueOf(teamColor)
            val attack = members.sumByDouble { it.offense ?: 0.0 }
            teamAttack.text = attack.toString()
            val defence = members.sumByDouble { it.defense ?: 0.0 }
            teamDefence.text = defence.toString()
            val ids = members.map { member ->
                val view = AvatarAndNameView(context, member)
                val generateViewId = View.generateViewId()
                view.id = generateViewId
                binding.teamWrapper.addView(view)
                memberViews.add(view)
                generateViewId
            }
            teamMembersFlow.referencedIds = ids.toIntArray()
        }
    }
}