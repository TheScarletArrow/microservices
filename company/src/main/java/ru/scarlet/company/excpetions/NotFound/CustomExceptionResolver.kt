package ru.scarlet.company.excpetions.NotFound

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component


@Component
class CustomExceptionResolver : DataFetcherExceptionResolverAdapter() {
    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return if (ex is CourseNotFoundException ||
            ex is DeanNotFoundException ||
            ex is DepartmentNotFoundException ||
            ex is FacultyNotFoundException ||
            ex is ProfessorNotFoundException
            ) {
            GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(ex.message)
                .path(env.executionStepInfo.path)
                .location(env.field.sourceLocation)
                .build()
        } else {
            null
        }
    }
}