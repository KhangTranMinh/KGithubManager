package com.ktm.kgithubmanager.data.network.mock

import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsResponse
import com.ktm.kgithubmanager.data.network.data.FetchUsersResponse

object MockResponse {

    fun fetchUsers(): FetchUsersResponse {
        return FetchUsersResponse().apply {
            addAll(
                arrayListOf(
                    FetchUsersResponse.UserResponse(
                        id = 1,
                        login = "Iron Man",
                        avatarUrl = "https://fastly.picsum.photos/id/923/200/200.jpg?hmac=3VHvOqFmO1AmGdpW-XcIVVb5CSOm5AwgyYRt9jYWAvo",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 2,
                        login = "Captain America",
                        avatarUrl = "https://fastly.picsum.photos/id/524/200/200.jpg?hmac=t6LNfKKZ41wUVh8ktcFHag3CGQDzovGpZquMO5cbH-o",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 3,
                        login = "Spider-Man",
                        avatarUrl = "https://fastly.picsum.photos/id/223/200/200.jpg?hmac=CNNyWbBcEAJ7TPkTmEEwdGrLFEYkxpTeVwJ7U0LB30Y",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 4,
                        login = "Deadpool",
                        avatarUrl = "https://fastly.picsum.photos/id/848/200/200.jpg?hmac=9pGbbeC1Q-zsi7TeMrGb93-TjKBmqPVY-tYuubIIqyw",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 5,
                        login = "Black Panther",
                        avatarUrl = "https://fastly.picsum.photos/id/266/200/200.jpg?hmac=gOu6kjZljo9d4wdKoXa6CepA5W07XTIynwZmcl-b1AM",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 6,
                        login = "Jessica Jones",
                        avatarUrl = "https://fastly.picsum.photos/id/14/300/300.jpg?hmac=Zgj0r31yv3kj_o-i2AcxpxFeHsInOW7dzqORfs8oWu0",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 7,
                        login = "Ant-Man",
                        avatarUrl = "https://fastly.picsum.photos/id/177/200/200.jpg?hmac=785Vry8HsdS9dQ7mFYbwV8bR2tWVtzJWWl9YLp6L0n8",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 8,
                        login = "Captain Marvel",
                        avatarUrl = "https://fastly.picsum.photos/id/813/200/200.jpg?hmac=KPqV3ldCcUrVRZi3-HZU491hRCs8AZAagGXxoAomcYU",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 9,
                        login = "Wolverine",
                        avatarUrl = "https://fastly.picsum.photos/id/361/200/200.jpg?hmac=8pPTUqe61Cxj4FYarGS9vZKtqUSjAzxOQJ0zBIHq28o",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 10,
                        login = "Luke Cage",
                        avatarUrl = "https://fastly.picsum.photos/id/21/200/200.jpg?hmac=a2iQ6UhOjpU6jn7QSsCpk1CiiKTxmW1R4UivDsv-n8o",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 11,
                        login = "Iron Man, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/923/200/200.jpg?hmac=3VHvOqFmO1AmGdpW-XcIVVb5CSOm5AwgyYRt9jYWAvo",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 12,
                        login = "Captain America, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/524/200/200.jpg?hmac=t6LNfKKZ41wUVh8ktcFHag3CGQDzovGpZquMO5cbH-o",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 13,
                        login = "Spider-Man, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/223/200/200.jpg?hmac=CNNyWbBcEAJ7TPkTmEEwdGrLFEYkxpTeVwJ7U0LB30Y",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 14,
                        login = "Deadpool, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/848/200/200.jpg?hmac=9pGbbeC1Q-zsi7TeMrGb93-TjKBmqPVY-tYuubIIqyw",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 15,
                        login = "Black Panther, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/266/200/200.jpg?hmac=gOu6kjZljo9d4wdKoXa6CepA5W07XTIynwZmcl-b1AM",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 16,
                        login = "Jessica Jones, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/14/300/300.jpg?hmac=Zgj0r31yv3kj_o-i2AcxpxFeHsInOW7dzqORfs8oWu0",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 17,
                        login = "Ant-Man, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/177/200/200.jpg?hmac=785Vry8HsdS9dQ7mFYbwV8bR2tWVtzJWWl9YLp6L0n8",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 18,
                        login = "Captain Marvel, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/813/200/200.jpg?hmac=KPqV3ldCcUrVRZi3-HZU491hRCs8AZAagGXxoAomcYU",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 19,
                        login = "Wolverine, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/361/200/200.jpg?hmac=8pPTUqe61Cxj4FYarGS9vZKtqUSjAzxOQJ0zBIHq28o",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                    FetchUsersResponse.UserResponse(
                        id = 20,
                        login = "Luke Cage, ver2",
                        avatarUrl = "https://fastly.picsum.photos/id/21/200/200.jpg?hmac=a2iQ6UhOjpU6jn7QSsCpk1CiiKTxmW1R4UivDsv-n8o",
                        htmlUrl = "https://fastly.picsum.photos/"
                    ),
                )
            )
        }
    }

    fun fetchUserDetails(userName: String): FetchUsersDetailsResponse {
        return FetchUsersDetailsResponse(
            id = 1,
            login = userName,
            avatarUrl = "https://fastly.picsum.photos/id/20/300/300.jpg?hmac=jE4J8fivrZv_MA5Xu9iSoEgNxfc_ucYlC_m6BgcSNNo",
            htmlUrl = "https://picsum.photos/",
            location = "Vietnam",
            followers = 100,
            following = 3483
        )
    }
}