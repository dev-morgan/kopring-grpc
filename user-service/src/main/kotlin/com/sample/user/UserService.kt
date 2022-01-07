package com.sample.user

import com.sample.models.UserGenreUpdateRequest
import com.sample.models.UserResponse
import com.sample.models.UserSearchRequest
import com.sample.models.UserServiceGrpc
import com.sample.models.common.Genre
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.transaction.annotation.Transactional

@GrpcService
class UserService(val userRepository: UserRepository) : UserServiceGrpc.UserServiceImplBase() {

    @Transactional(readOnly = true)
    override fun getUserGenre(request: UserSearchRequest, responseObserver: StreamObserver<UserResponse>) {
        val builder = UserResponse.newBuilder()
        userRepository.findById(request.loginId)
            .ifPresent { user ->
                updateUser(builder, user)
            }
        responseObserver.onNext(builder.build())
        responseObserver.onCompleted()
    }

    @Transactional
    override fun updateUserGenre(request: UserGenreUpdateRequest, responseObserver: StreamObserver<UserResponse>) {
        val builder = UserResponse.newBuilder()
        userRepository.findById(request.loginId)
            .ifPresent { user ->
                user.genre = request.genre.toString()
                updateUser(builder, user)
            }
        responseObserver.onNext(builder.build())
        responseObserver.onCompleted()
    }

    private fun updateUser(builder: UserResponse.Builder, user: User) {
        builder.apply {
            username = user.username
            nick = user.nick
            genre = Genre.valueOf(user.genre!!.uppercase())
        }
    }
}
