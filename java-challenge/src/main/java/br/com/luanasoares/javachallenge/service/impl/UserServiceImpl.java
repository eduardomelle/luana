package br.com.luanasoares.javachallenge.service.impl;

import br.com.luanasoares.javachallenge.dto.MovieDto;
import br.com.luanasoares.javachallenge.dto.UserAddFavoriteResponsetDto;
import br.com.luanasoares.javachallenge.dto.UserFindAllResponseDto;
import br.com.luanasoares.javachallenge.dto.UserRemoveFavoriteResponsetDto;
import br.com.luanasoares.javachallenge.model.Movie;
import br.com.luanasoares.javachallenge.model.Role;
import br.com.luanasoares.javachallenge.model.User;
import br.com.luanasoares.javachallenge.repository.MovieRepository;
import br.com.luanasoares.javachallenge.repository.RoleRepository;
import br.com.luanasoares.javachallenge.repository.UserRepository;
import br.com.luanasoares.javachallenge.service.UserService;
import br.com.luanasoares.javachallenge.util.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final MovieRepository movieRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			MovieRepository movieRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.movieRepository = movieRepository;
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public User save(String username, String password) {
		Set<Role> roles = new HashSet<>();
		roles.add(this.roleRepository.findByName("ROLE_USER"));

		User user = new User();
		user.setUsername(username);
		user.setPassword(PasswordUtils.generatebCrypt(password));
		user.setRoles(roles);
		return this.userRepository.save(user);
	}

	@Override
	public List<UserFindAllResponseDto> findAll() {
		List<UserFindAllResponseDto> userFindAllResponseDtos = new ArrayList<>();
		this.userRepository.findAll().forEach(user -> {
			UserFindAllResponseDto userFindAllResponseDto = new UserFindAllResponseDto();
			BeanUtils.copyProperties(user, userFindAllResponseDto);
			userFindAllResponseDtos.add(userFindAllResponseDto);
		});
		return userFindAllResponseDtos;
	}

	@Override
	public User update(Long id, Set<String> roles) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (optionalUser.isPresent()) {
			Set<Role> rs = new HashSet<>();
			roles.forEach(role -> {
				Role r = this.roleRepository.findByName(role);
				if (r != null) {
					rs.add(r);
				}
			});

			if (!rs.isEmpty()) {
				User user = optionalUser.get();
				user.setRoles(rs);
				return this.userRepository.save(user);
			}
		}

		return null;
	}

	@Override
	public UserAddFavoriteResponsetDto addFavorite(Long id, Long movieId) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			Optional<Movie> optionalMovie = this.movieRepository.findById(movieId);
			if (optionalMovie.isPresent()) {
				Movie movie = optionalMovie.get();
				movie.setStar(movie.getStar() + 1);
				Movie movieSaved = this.movieRepository.save(movie);

				user.getFavorites().add(movieSaved);
				User userSaved = this.userRepository.save(user);

				List<MovieDto> favorites = new ArrayList<>();
				userSaved.getFavorites().forEach(favorite -> {
					MovieDto movieDto = new MovieDto();
					movieDto.setTitle(favorite.getTitle());
					movieDto.setStar(favorite.getStar());
					favorites.add(movieDto);
				});

				UserAddFavoriteResponsetDto userAddFavoriteResponsetDto = new UserAddFavoriteResponsetDto();
				userAddFavoriteResponsetDto.setUsername(userSaved.getUsername());
				userAddFavoriteResponsetDto.setFavorites(favorites);
				return userAddFavoriteResponsetDto;
			}
		}

		return null;
	}

	@Override
	public UserRemoveFavoriteResponsetDto removeFavorite(Long id, Long movieId) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			Optional<Movie> optionalMovie = this.movieRepository.findById(movieId);
			if (optionalMovie.isPresent()) {
				Movie movie = optionalMovie.get();
				if (movie.getStar() != 0) {
					movie.setStar(movie.getStar() - 1);
					this.movieRepository.save(movie);
				}

				Set<Movie> favorites = new HashSet<>();
				for (Movie favorite : user.getFavorites()) {
					if (!favorite.getId().equals(movieId)) {
						Optional<Movie> om = this.movieRepository.findById(favorite.getId());
						if (om.isPresent()) {
							favorites.add(om.get());
						}
					}
				}
				user.setFavorites(favorites);
				User userSaved = this.userRepository.save(user);

				List<MovieDto> movieDtos = new ArrayList<>();
				userSaved.getFavorites().forEach(favorite -> {
					MovieDto movieDto = new MovieDto();
					movieDto.setTitle(favorite.getTitle());
					movieDto.setStar(favorite.getStar());
					movieDtos.add(movieDto);
				});

				UserRemoveFavoriteResponsetDto userRemoveFavoriteResponsetDto = new UserRemoveFavoriteResponsetDto();
				userRemoveFavoriteResponsetDto.setUsername(userSaved.getUsername());
				userRemoveFavoriteResponsetDto.setFavorites(movieDtos);
				return userRemoveFavoriteResponsetDto;
			}
		}

		return null;
	}

	@Override
	public List<MovieDto> findFavoritesById(Long id) {
		List<MovieDto> favorites = new ArrayList<>();
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.getFavorites().forEach(favorite -> {
				MovieDto movieDto = new MovieDto();
				movieDto.setTitle(favorite.getTitle());
				movieDto.setStar(favorite.getStar());
				favorites.add(movieDto);
			});
		}

		return favorites;
	}

}
