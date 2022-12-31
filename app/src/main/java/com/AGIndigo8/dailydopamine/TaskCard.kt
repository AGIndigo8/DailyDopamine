package com.AGIndigo8.dailydopamine

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.LinearLayout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.core.content.ContextCompat

@Composable
fun TaskCard(task: Task) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TaskCardRow(task)
    }
}

@Composable
fun TaskCardRow(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TaskColLeft(task)
        TaskColCenter()
        TaskColRight()
    }
}

@Composable
fun TaskColLeft(task: Task){
    Column(
        modifier = Modifier
            .fillMaxWidth(0.2f)
            .wrapContentWidth(Alignment.Start),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = task.name,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = task.description,
            color = Color.Gray
        )
    }
}

@Composable
fun TaskColCenter(){
    Column(
        modifier = Modifier
            .fillMaxWidth(0.2f)
            .wrapContentWidth(Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Task Points",
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.brainp),
            contentDescription = "Task Complete",
            tint = Color.Green
        )
    }
}

@Composable
fun TaskColRight(){
    Column(
        modifier = Modifier
            .fillMaxWidth(.2f)
            .wrapContentWidth(Alignment.End),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Task Time",
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Task Date",
            color = Color.Gray
        )
    }
}


@Preview
@Composable
fun TaskCardPreview() {
    var task = Task()
    task.name = "Play Guitar"
    task.description = "Learning to play guitar"
    TaskCard(task)
}