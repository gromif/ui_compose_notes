package com.nevidimka655.notes.di

import com.nevidimka655.notes.data.util.AeadHandler
import com.nevidimka655.notes.data.util.AeadUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gromif.crypto.tink.core.encoders.Base64Encoder
import io.gromif.crypto.tink.data.AssociatedDataManager
import io.gromif.crypto.tink.data.KeysetManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UtilModule {

    @Provides
    fun provideAeadUtil(
        keysetManager: KeysetManager,
        associatedDataManager: AssociatedDataManager,
        base64Encoder: Base64Encoder,
    ): AeadUtil = AeadUtil(
        keysetManager = keysetManager,
        associatedDataManager = associatedDataManager,
        base64Encoder = base64Encoder,
    )

    @Singleton
    @Provides
    fun provideAeadHandler(aeadUtil: AeadUtil): AeadHandler = AeadHandler(aeadUtil)

}