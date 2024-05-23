package components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import grayBackgroundColor
import greenColor
import redColor
import secondaryBackgroundColor
import components.animation.KottieAnimationSettings
import components.animation.KottieAnimationView
import components.animation.KottieCompositionSpecType
import components.button.ButtonState.DEFAULT
import components.button.ButtonState.ERROR
import components.button.ButtonState.INIT_LOADING
import components.button.ButtonState.SEND_REQUEST
import components.button.ButtonState.SUCCESS

/**
 * BUTTON
 */
@Composable
fun StateButton(
    text: String,
    modifier: Modifier = Modifier,
    state: ButtonState = DEFAULT,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    onSuccessCallback: () -> Unit = {},
    onErrorCallback: () -> Unit = {}
) {
    val successErrorColor by animateColorAsState(
        targetValue = when (state) {
            ERROR -> MaterialTheme.colors.redColor
            SUCCESS -> MaterialTheme.colors.greenColor
            else -> MaterialTheme.colors.grayBackgroundColor
        },
        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
    )
    val backgroundColor = when (state) {
        INIT_LOADING -> MaterialTheme.colors.secondaryBackgroundColor.copy(alpha = 0.9f)
        DEFAULT -> when {
            enabled -> MaterialTheme.colors.grayBackgroundColor
            else -> MaterialTheme.colors.secondaryBackgroundColor.copy(alpha = 0.9f)
        }
        SEND_REQUEST -> MaterialTheme.colors.grayBackgroundColor
        SUCCESS, ERROR -> successErrorColor
    }

    Button(
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = backgroundColor
        ),
        onClick = {
            if (state == DEFAULT) onClick.invoke()
        },
        modifier = modifier.clip(MaterialTheme.shapes.large)
    ) {
        when (state) {
            DEFAULT -> DefaultText(
                text = text,
                enabled = enabled,
                state = state
            )
            SUCCESS, ERROR -> AnimatedIcon(
                state = state,
                backgroundColor = backgroundColor,
                onSuccessCallback = onSuccessCallback,
                onErrorCallback = onErrorCallback
            )
            INIT_LOADING, SEND_REQUEST -> Progress(state)
        }
    }
}

/**
 * BUTTON TEXT
 */
@Composable
private fun DefaultText(
    text: String,
    enabled: Boolean,
    state: ButtonState
) {
    Text(
        text = text,
        style = MaterialTheme.typography.button,
        color = when (state) {
            DEFAULT, INIT_LOADING -> if (enabled) Color.White else MaterialTheme.colors.secondaryBackgroundColor
            else -> MaterialTheme.colors.secondaryBackgroundColor
        }
    )
}

/**
 * CIRCULAR PROGRESS
 */
@Composable
private fun Progress(state: ButtonState) = CircularProgressIndicator(
    strokeWidth = 2.dp,
    modifier = Modifier.size(20.dp),
    color = when {
        state == INIT_LOADING -> MaterialTheme.colors.secondaryBackgroundColor
        else -> Color.White
    }
)

/**
 * ANIMATE DRAWING ICON
 */
@Composable
private fun AnimatedIcon(
    state: ButtonState,
    backgroundColor: Color, // TODO: https://github.com/ismai117/kottie/issues/5
    onSuccessCallback: () -> Unit,
    onErrorCallback: () -> Unit
) {
    val animationJsonString = when (state) { // TODO: Find out how to work with files in KMM
        ERROR -> errorAnimationJson
        SUCCESS -> successAnimationJson
        else -> throw IllegalStateException("Wrong ButtonState")
    }
    val onCompleteCallback = when (state) {
        ERROR -> onErrorCallback
        SUCCESS -> onSuccessCallback
        else -> throw IllegalStateException("Wrong ButtonState")
    }
    KottieAnimationView(
        type = KottieCompositionSpecType.JsonString(animationJsonString),
        animationSettings = KottieAnimationSettings(backgroundColor = backgroundColor),
        onCompleteCallback = onCompleteCallback,
        modifier = Modifier.size(24.dp)
    )
}

/**
 * BUTTON STATE TYPES
 */
enum class ButtonState {
    INIT_LOADING,
    DEFAULT,
    SEND_REQUEST,
    SUCCESS,
    ERROR
}

private val errorAnimationJson = """
    {
      "v": "5.6.2",
      "fr": 60,
      "ip": 0,
      "op": 60,
      "w": 200,
      "h": 200,
      "nm": "Cancel",
      "ddd": 0,
      "assets": [],
      "layers": [
        {
          "ddd": 0,
          "ind": 1,
          "ty": 4,
          "nm": "Shape Layer 3",
          "sr": 1,
          "ks": {
            "o": {
              "a": 0,
              "k": 100,
              "ix": 11
            },
            "r": {
              "a": 0,
              "k": 90,
              "ix": 10
            },
            "p": {
              "a": 0,
              "k": [
                100,
                100,
                0
              ],
              "ix": 2
            },
            "a": {
              "a": 0,
              "k": [
                0,
                0,
                0
              ],
              "ix": 1
            },
            "s": {
              "a": 0,
              "k": [
                100,
                100,
                100
              ],
              "ix": 6
            }
          },
          "ao": 0,
          "shapes": [
            {
              "ty": "gr",
              "it": [
                {
                  "ind": 0,
                  "ty": "sh",
                  "ix": 1,
                  "ks": {
                    "a": 0,
                    "k": {
                      "i": [
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ]
                      ],
                      "o": [
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ]
                      ],
                      "v": [
                        [
                          -52,
                          -51.5
                        ],
                        [
                          52,
                          52
                        ]
                      ],
                      "c": false
                    },
                    "ix": 2
                  },
                  "nm": "Path 1",
                  "mn": "ADBE Vector Shape - Group",
                  "hd": false
                },
                {
                  "ty": "st",
                  "c": {
                    "a": 0,
                    "k": [
                      1,
                      1,
                      1,
                      1
                    ],
                    "ix": 3
                  },
                  "o": {
                    "a": 0,
                    "k": 100,
                    "ix": 4
                  },
                  "w": {
                    "a": 0,
                    "k": 20,
                    "ix": 5
                  },
                  "lc": 2,
                  "lj": 1,
                  "ml": 4,
                  "bm": 0,
                  "nm": "Stroke 1",
                  "mn": "ADBE Vector Graphic - Stroke",
                  "hd": false
                },
                {
                  "ty": "tr",
                  "p": {
                    "a": 0,
                    "k": [
                      0,
                      0
                    ],
                    "ix": 2
                  },
                  "a": {
                    "a": 0,
                    "k": [
                      0,
                      0
                    ],
                    "ix": 1
                  },
                  "s": {
                    "a": 0,
                    "k": [
                      100,
                      100
                    ],
                    "ix": 3
                  },
                  "r": {
                    "a": 0,
                    "k": 0,
                    "ix": 6
                  },
                  "o": {
                    "a": 0,
                    "k": 100,
                    "ix": 7
                  },
                  "sk": {
                    "a": 0,
                    "k": 0,
                    "ix": 4
                  },
                  "sa": {
                    "a": 0,
                    "k": 0,
                    "ix": 5
                  },
                  "nm": "Transform"
                }
              ],
              "nm": "Shape 1",
              "np": 3,
              "cix": 2,
              "bm": 0,
              "ix": 1,
              "mn": "ADBE Vector Group",
              "hd": false
            },
            {
              "ty": "tm",
              "s": {
                "a": 1,
                "k": [
                  {
                    "i": {
                      "x": [
                        0.3
                      ],
                      "y": [
                        1
                      ]
                    },
                    "o": {
                      "x": [
                        0.707
                      ],
                      "y": [
                        0
                      ]
                    },
                    "t": 0,
                    "s": [
                      100
                    ]
                  },
                  {
                    "t": 30,
                    "s": [
                      0
                    ]
                  }
                ],
                "ix": 1
              },
              "e": {
                "a": 0,
                "k": 100,
                "ix": 2
              },
              "o": {
                "a": 0,
                "k": 0,
                "ix": 3
              },
              "m": 1,
              "ix": 2,
              "nm": "Trim Paths 1",
              "mn": "ADBE Vector Filter - Trim",
              "hd": false
            }
          ],
          "ip": 0,
          "op": 300,
          "st": 0,
          "bm": 0
        },
        {
          "ddd": 0,
          "ind": 2,
          "ty": 4,
          "nm": "Shape Layer 2",
          "sr": 1,
          "ks": {
            "o": {
              "a": 0,
              "k": 100,
              "ix": 11
            },
            "r": {
              "a": 0,
              "k": 0,
              "ix": 10
            },
            "p": {
              "a": 0,
              "k": [
                100,
                100,
                0
              ],
              "ix": 2
            },
            "a": {
              "a": 0,
              "k": [
                0,
                0,
                0
              ],
              "ix": 1
            },
            "s": {
              "a": 0,
              "k": [
                100,
                100,
                100
              ],
              "ix": 6
            }
          },
          "ao": 0,
          "shapes": [
            {
              "ty": "gr",
              "it": [
                {
                  "ind": 0,
                  "ty": "sh",
                  "ix": 1,
                  "ks": {
                    "a": 0,
                    "k": {
                      "i": [
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ]
                      ],
                      "o": [
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ]
                      ],
                      "v": [
                        [
                          -52,
                          -51.5
                        ],
                        [
                          52,
                          52
                        ]
                      ],
                      "c": false
                    },
                    "ix": 2
                  },
                  "nm": "Path 1",
                  "mn": "ADBE Vector Shape - Group",
                  "hd": false
                },
                {
                  "ty": "st",
                  "c": {
                    "a": 0,
                    "k": [
                      1,
                      1,
                      1,
                      1
                    ],
                    "ix": 3
                  },
                  "o": {
                    "a": 0,
                    "k": 100,
                    "ix": 4
                  },
                  "w": {
                    "a": 0,
                    "k": 20,
                    "ix": 5
                  },
                  "lc": 2,
                  "lj": 1,
                  "ml": 4,
                  "bm": 0,
                  "nm": "Stroke 1",
                  "mn": "ADBE Vector Graphic - Stroke",
                  "hd": false
                },
                {
                  "ty": "tr",
                  "p": {
                    "a": 0,
                    "k": [
                      0,
                      0
                    ],
                    "ix": 2
                  },
                  "a": {
                    "a": 0,
                    "k": [
                      0,
                      0
                    ],
                    "ix": 1
                  },
                  "s": {
                    "a": 0,
                    "k": [
                      100,
                      100
                    ],
                    "ix": 3
                  },
                  "r": {
                    "a": 0,
                    "k": 0,
                    "ix": 6
                  },
                  "o": {
                    "a": 0,
                    "k": 100,
                    "ix": 7
                  },
                  "sk": {
                    "a": 0,
                    "k": 0,
                    "ix": 4
                  },
                  "sa": {
                    "a": 0,
                    "k": 0,
                    "ix": 5
                  },
                  "nm": "Transform"
                }
              ],
              "nm": "Shape 1",
              "np": 3,
              "cix": 2,
              "bm": 0,
              "ix": 1,
              "mn": "ADBE Vector Group",
              "hd": false
            },
            {
              "ty": "tm",
              "s": {
                "a": 0,
                "k": 0,
                "ix": 1
              },
              "e": {
                "a": 1,
                "k": [
                  {
                    "i": {
                      "x": [
                        0.3
                      ],
                      "y": [
                        1
                      ]
                    },
                    "o": {
                      "x": [
                        0.707
                      ],
                      "y": [
                        0
                      ]
                    },
                    "t": 0,
                    "s": [
                      0
                    ]
                  },
                  {
                    "t": 30,
                    "s": [
                      100
                    ]
                  }
                ],
                "ix": 2
              },
              "o": {
                "a": 0,
                "k": 0,
                "ix": 3
              },
              "m": 1,
              "ix": 2,
              "nm": "Trim Paths 1",
              "mn": "ADBE Vector Filter - Trim",
              "hd": false
            }
          ],
          "ip": 0,
          "op": 300,
          "st": 0,
          "bm": 0
        }
      ],
      "markers": []
    }
""".trimIndent()

private val successAnimationJson = """
    {
      "v": "5.6.2",
      "fr": 60,
      "ip": 0,
      "op": 60,
      "w": 200,
      "h": 200,
      "nm": "Comp 1",
      "ddd": 0,
      "assets": [],
      "layers": [
        {
          "ddd": 0,
          "ind": 1,
          "ty": 4,
          "nm": "Shape Layer 1",
          "sr": 1,
          "ks": {
            "o": {
              "a": 0,
              "k": 100,
              "ix": 11
            },
            "r": {
              "a": 0,
              "k": 0,
              "ix": 10
            },
            "p": {
              "a": 0,
              "k": [
                101,
                100,
                0
              ],
              "ix": 2
            },
            "a": {
              "a": 0,
              "k": [
                0,
                0,
                0
              ],
              "ix": 1
            },
            "s": {
              "a": 0,
              "k": [
                100,
                100,
                100
              ],
              "ix": 6
            }
          },
          "ao": 0,
          "shapes": [
            {
              "ty": "gr",
              "it": [
                {
                  "ind": 0,
                  "ty": "sh",
                  "ix": 1,
                  "ks": {
                    "a": 0,
                    "k": {
                      "i": [
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ]
                      ],
                      "o": [
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ],
                        [
                          0,
                          0
                        ]
                      ],
                      "v": [
                        [
                          -65.5,
                          3
                        ],
                        [
                          -26,
                          48.5
                        ],
                        [
                          63.5,
                          -55.5
                        ]
                      ],
                      "c": false
                    },
                    "ix": 2
                  },
                  "nm": "Path 1",
                  "mn": "ADBE Vector Shape - Group",
                  "hd": false
                },
                {
                  "ty": "st",
                  "c": {
                    "a": 0,
                    "k": [
                      1,
                      1,
                      1,
                      1
                    ],
                    "ix": 3
                  },
                  "o": {
                    "a": 0,
                    "k": 100,
                    "ix": 4
                  },
                  "w": {
                    "a": 0,
                    "k": 20,
                    "ix": 5
                  },
                  "lc": 2,
                  "lj": 2,
                  "bm": 0,
                  "nm": "Stroke 1",
                  "mn": "ADBE Vector Graphic - Stroke",
                  "hd": false
                },
                {
                  "ty": "tr",
                  "p": {
                    "a": 0,
                    "k": [
                      0,
                      0
                    ],
                    "ix": 2
                  },
                  "a": {
                    "a": 0,
                    "k": [
                      0,
                      0
                    ],
                    "ix": 1
                  },
                  "s": {
                    "a": 0,
                    "k": [
                      100,
                      100
                    ],
                    "ix": 3
                  },
                  "r": {
                    "a": 0,
                    "k": 0,
                    "ix": 6
                  },
                  "o": {
                    "a": 0,
                    "k": 100,
                    "ix": 7
                  },
                  "sk": {
                    "a": 0,
                    "k": 0,
                    "ix": 4
                  },
                  "sa": {
                    "a": 0,
                    "k": 0,
                    "ix": 5
                  },
                  "nm": "Transform"
                }
              ],
              "nm": "Shape 1",
              "np": 3,
              "cix": 2,
              "bm": 0,
              "ix": 1,
              "mn": "ADBE Vector Group",
              "hd": false
            },
            {
              "ty": "tm",
              "s": {
                "a": 0,
                "k": 0,
                "ix": 1
              },
              "e": {
                "a": 1,
                "k": [
                  {
                    "i": {
                      "x": [
                        0.281
                      ],
                      "y": [
                        1
                      ]
                    },
                    "o": {
                      "x": [
                        0.707
                      ],
                      "y": [
                        0
                      ]
                    },
                    "t": 0,
                    "s": [
                      0
                    ]
                  },
                  {
                    "t": 30,
                    "s": [
                      100
                    ]
                  }
                ],
                "ix": 2
              },
              "o": {
                "a": 0,
                "k": 0,
                "ix": 3
              },
              "m": 1,
              "ix": 2,
              "nm": "Trim Paths 1",
              "mn": "ADBE Vector Filter - Trim",
              "hd": false
            }
          ],
          "ip": 0,
          "op": 300,
          "st": 0,
          "bm": 0
        }
      ],
      "markers": []
    }
""".trimIndent()