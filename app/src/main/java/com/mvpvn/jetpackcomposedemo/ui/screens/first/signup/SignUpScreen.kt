package com.mvpvn.jetpackcomposedemo.ui.screens.first.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.mvpvn.jetpackcomposedemo.R
import com.mvpvn.jetpackcomposedemo.core.extension.toSp
import com.mvpvn.jetpackcomposedemo.ui.theme.text
import com.mvpvn.jetpackcomposedemo.ui.theme.textBold
import com.mvpvn.jetpackcomposedemo.data.local.provider.provideDimensions

@Composable
fun SignUpScreen(navigateSignInScreen: () -> Unit) {
    val provideDimension = provideDimensions()

    val textUserNameState = remember { mutableStateOf("") }
    val textEmailState = remember { mutableStateOf("") }
    val textPasswordState = remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = provideDimension.dp36)
            .verticalScroll(rememberScrollState())
    ) {

        val (titleSignUp, titleOrWith, signUp) = createRefs()
        val (textFieldUserName, textFieldEmail, textFieldPassword) = createRefs()
        val (buttonLogin) = createRefs()
        val (dividerOrWith, spacerAdditionalLoginMethod) = createRefs()
        val (dividerTextFieldUsername, dividerTextFieldEmail, dividerTextFieldPassword) = createRefs()
        val (imageGoogle, imageFacebook) = createRefs()

        Text(
            text = stringResource(id = R.string.sign_up),
            modifier = Modifier
                .systemBarsPadding()
                .constrainAs(titleSignUp) {
                    top.linkTo(
                        anchor = parent.top,
                        margin = provideDimension.dp70
                    )
                    start.linkTo(parent.start)
                },
            color = colorResource(id = R.color.button_color),
            fontSize = R.dimen.sp36.toSp(),
            fontWeight = FontWeight.ExtraBold,
            style = textBold
        )

        BasicTextField(
            value = textUserNameState.value,
            onValueChange = { textUserNameState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textFieldUserName) {
                    top.linkTo(
                        anchor = titleSignUp.bottom,
                        margin = provideDimension.dp45
                    )
                    start.linkTo(parent.start)
                },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = provideDimension.dp15,
                            horizontal = provideDimension.dp3
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "Email Icon",
                        tint = colorResource(id = R.color.hint)
                    )
                    Spacer(modifier = Modifier.width(provideDimension.dp15))
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                        if (textUserNameState.value.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.input_username),
                                style = text.copy(
                                    color = colorResource(id = R.color.text_hint),
                                    fontSize = R.dimen.sp18.toSp()
                                )
                            )
                        }
                    }
                }
            },
            singleLine = true,
            textStyle = text.copy(
                color = colorResource(id = R.color.black),
                fontSize = R.dimen.sp18.toSp()
            ),
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(dividerTextFieldUsername) {
                    top.linkTo(
                        anchor = textFieldUserName.bottom,
                    )
                },
            color = colorResource(id = R.color.divider),
            thickness = dimensionResource(id = R.dimen.dp1)
        )

        BasicTextField(
            value = textEmailState.value,
            onValueChange = { textEmailState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textFieldEmail) {
                    top.linkTo(
                        anchor = textFieldUserName.bottom,
                        margin = provideDimension.dp32
                    )
                    start.linkTo(parent.start)
                },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = provideDimension.dp15,
                            horizontal = provideDimension.dp3
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Password Icon",
                        tint = colorResource(id = R.color.hint)
                    )
                    Spacer(modifier = Modifier.width(provideDimension.dp15))
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                        if (textEmailState.value.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.input_email_id),
                                style = text.copy(
                                    color = colorResource(id = R.color.text_hint),
                                    fontSize = R.dimen.sp18.toSp()
                                )
                            )
                        }
                    }
                }
            },
            singleLine = true,
            textStyle = text.copy(
                color = colorResource(id = R.color.black),
                fontSize = R.dimen.sp18.toSp()
            ),
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(dividerTextFieldEmail) {
                    top.linkTo(
                        anchor = textFieldEmail.bottom,
                    )
                },
            color = colorResource(id = R.color.divider),
            thickness = dimensionResource(id = R.dimen.dp1)
        )

        BasicTextField(
            value = textPasswordState.value,
            onValueChange = { textPasswordState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textFieldPassword) {
                    top.linkTo(
                        anchor = textFieldEmail.bottom,
                        margin = provideDimension.dp32
                    )
                    start.linkTo(parent.start)
                },
            visualTransformation = PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = provideDimension.dp15,
                            horizontal = provideDimension.dp3
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Password Icon",
                        tint = colorResource(id = R.color.hint)
                    )
                    Spacer(modifier = Modifier.width(provideDimension.dp15))
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                        if (textPasswordState.value.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.input_password),
                                style = text.copy(
                                    color = colorResource(id = R.color.text_hint),
                                    fontSize = R.dimen.sp18.toSp()
                                )
                            )
                        }
                    }
                }
            },
            singleLine = true,
            textStyle = text.copy(
                color = colorResource(id = R.color.black),
                fontSize = R.dimen.sp18.toSp()
            ),
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(dividerTextFieldPassword) {
                    top.linkTo(
                        anchor = textFieldPassword.bottom,
                    )
                },
            color = colorResource(id = R.color.divider),
            thickness = dimensionResource(id = R.dimen.dp1)
        )

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(provideDimension.dp52)
                .constrainAs(buttonLogin) {
                    top.linkTo(
                        anchor = textFieldPassword.bottom,
                        margin = provideDimension.dp58
                    )
                },
            shape = RoundedCornerShape(provideDimension.dp14),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_color))
        ) {
            Text(
                text = stringResource(id = R.string.create),
                color = colorResource(id = R.color.white),
                fontSize = R.dimen.sp18.toSp(),
                style = textBold
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(dividerOrWith) {
                    top.linkTo(
                        anchor = buttonLogin.bottom,
                        margin = provideDimension.dp55
                    )
                },
            color = colorResource(id = R.color.divider),
            thickness = dimensionResource(id = R.dimen.dp1)
        )

        Text(
            text = stringResource(id = R.string.or_with),
            modifier = Modifier
                .background(color = colorResource(id = R.color.white))
                .padding(horizontal = dimensionResource(id = R.dimen.dp22))
                .constrainAs(titleOrWith) {
                    top.linkTo(anchor = dividerOrWith.top)
                    bottom.linkTo(anchor = dividerOrWith.bottom)
                    start.linkTo(anchor = dividerOrWith.start)
                    end.linkTo(anchor = dividerOrWith.end)
                },
            style = text,
            fontSize = R.dimen.sp14.toSp(),
            color = colorResource(id = R.color.text_hint)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "",
            modifier = Modifier.constrainAs(imageGoogle) {
                top.linkTo(
                    anchor = titleOrWith.bottom,
                    margin = provideDimension.dp38
                )
                start.linkTo(anchor = parent.start)
                end.linkTo(anchor = spacerAdditionalLoginMethod.start)
            }
        )
        Spacer(
            modifier = Modifier
                .width(provideDimension.dp16)
                .constrainAs(spacerAdditionalLoginMethod) {
                    top.linkTo(imageGoogle.top)
                    bottom.linkTo(imageGoogle.bottom)
                    start.linkTo(imageGoogle.end)
                    end.linkTo(imageFacebook.start)
                })
        Image(
            painter = painterResource(id = R.drawable.ic_facebook),
            contentDescription = "",
            modifier = Modifier.constrainAs(imageFacebook) {
                top.linkTo(anchor = imageGoogle.top)
                bottom.linkTo(anchor = imageGoogle.bottom)
                start.linkTo(anchor = spacerAdditionalLoginMethod.end)
                end.linkTo(anchor = parent.end)
            }
        )
        createHorizontalChain(
            imageGoogle,
            spacerAdditionalLoginMethod,
            imageFacebook,
            chainStyle = ChainStyle.Packed
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.dark_blue),
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    )
                ) {
                    append(stringResource(id = R.string.have_any_account))
                }
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.splash_greeting),
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append(" ${stringResource(id = R.string.sign_in)}")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigateSignInScreen() }
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(bottom = dimensionResource(id = R.dimen.dp10))
                .navigationBarsPadding()
                .constrainAs(signUp) {
                    top.linkTo(
                        anchor = imageGoogle.bottom,
                        margin = provideDimension.dp95
                    )
                },
            fontSize = R.dimen.sp14.toSp()
        )
    }
}