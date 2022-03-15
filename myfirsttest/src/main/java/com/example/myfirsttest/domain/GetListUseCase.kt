package com.example.myfirsttest.domain

class GetListUseCase(
    private val useCaseRepository: UseCaseRepository,
) {

    fun execute(): List<UseCaseModel> {
        return useCaseRepository.getList()
    }
}